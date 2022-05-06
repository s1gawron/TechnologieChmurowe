package com.s1gawron.technologiechmurowe;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class InfoController {

    public final TimezoneDataProvider timezoneDataProvider;

    @GetMapping
    public String printInfo(final HttpServletRequest httpServletRequest) {
        final String clientIpAddress = HttpUtils.getRequestIP(httpServletRequest);

        final ZonedDateTime nowInClientTimezone = ZonedDateTime.now(ZoneId.of(timezoneDataProvider.getTimezone(clientIpAddress)));
        return "Adres IP klient: " + clientIpAddress +
            "<br/>Aktualny czas w strefie czasowej klient: " + nowInClientTimezone;
    }

}
