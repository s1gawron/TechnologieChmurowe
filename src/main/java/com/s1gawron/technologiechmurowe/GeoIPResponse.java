package com.s1gawron.technologiechmurowe;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonDeserialize(builder = GeoIPResponse.GeoIPResponseBuilder.class)
public class GeoIPResponse {

    private final String query;

    private final String status;

    private final String country;

    private final String countryCode;

    private final String region;

    private final String regionName;

    private final String city;

    private final String zip;

    private final String lat;

    private final String lon;

    private final String timezone;

    private final String isp;

    private final String org;

    private final String as;

    @JsonPOJOBuilder(withPrefix = "")
    public static class GeoIPResponseBuilder {

    }

}
