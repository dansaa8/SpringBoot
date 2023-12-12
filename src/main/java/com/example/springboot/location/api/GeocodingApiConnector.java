package com.example.springboot.location.api;

import com.example.springboot.config.ConfigProperties;
import com.example.springboot.location.service.LocationQueryService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableRetry
public class GeocodingApiConnector {

    RestClient restClient;
    final ConfigProperties configProperties;
    final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(LocationQueryService.class);

    public GeocodingApiConnector(RestClient.Builder restClient, ConfigProperties configProperties, ObjectMapper objectMapper) {
        this.restClient = restClient.baseUrl(configProperties.geo_base_url()).build();
        this.configProperties = configProperties;
        this.objectMapper = objectMapper;
    }

    @Retryable(retryFor = {RestClientException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public Map<String, Object> getAddress(float lat, float lon) {
        String jsonString = restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/reverse")
                        .queryParam("lat", lat)
                        .queryParam("lon", lon)
                        .build())
                .retrieve()
                .body(String.class);
        logger.info("json-strängen = {}", jsonString);

        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            JsonNode addressNode = jsonNode.get("address");

            Map<String, Object> addressMap = objectMapper.convertValue(addressNode, Map.class);

            return addressMap;
        } catch (Exception e) {
            logger.error("Error processing JSON", e);
            return new HashMap<>();
        }
    }

    @Recover
    public Map<String, Object> recoverMethod(RestClientException e) {
        return new HashMap<>();
    }
}
