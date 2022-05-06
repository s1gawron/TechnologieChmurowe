package com.s1gawron.technologiechmurowe;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class TimezoneDataProvider {

    public String getTimezone(final String query) {
        final ResponseEntity<GeoIPResponse> response = getWebClient().get()
            .uri(uriBuilder -> uriBuilder
                .queryParam("query", query).build())
            .retrieve()
            .toEntity(GeoIPResponse.class)
            .block();

        return response.getBody().getTimezone();
    }


    private WebClient getWebClient() {
        final HttpClient httpClient = HttpClient.create()
            .resolver(DefaultAddressResolverGroup.INSTANCE)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofMillis(5000))
            .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .baseUrl("http://ip-api.com/json/")
            .defaultHeaders(headers -> headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
            .build();
    }
}
