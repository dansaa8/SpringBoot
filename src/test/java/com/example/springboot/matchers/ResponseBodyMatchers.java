package com.example.springboot.matchers;

import com.example.springboot.deserializer.Point2DDeserializer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.geolatte.geom.Point;
import org.springframework.test.web.servlet.ResultMatcher;


import static org.assertj.core.api.Assertions.assertThat;

public class ResponseBodyMatchers {

    private final ObjectMapper objectMapper;
    private final Point2DDeserializer point2DDeserializer;

    public ResponseBodyMatchers() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Initialize your custom deserializer
        point2DDeserializer = new Point2DDeserializer();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Point.class, point2DDeserializer);
        objectMapper.registerModule(simpleModule);
    }

    public <T> ResultMatcher containsObjectAsJson(
            Object expectedObject,
            Class<T> targetClass) {
        return mvcResult -> {
            String json = mvcResult.getResponse().getContentAsString();
            T actualObject = objectMapper.readValue(json, targetClass);
            assertThat(actualObject).usingRecursiveComparison().isEqualTo(expectedObject);
        };
    }

    public <T> ResultMatcher containsObjectAsJson(
            Object expectedObject,
            TypeReference<T> typeReference) {
        return mvcResult -> {
            String json = mvcResult.getResponse().getContentAsString();
            T actualObject = objectMapper.readValue(json, typeReference);
            assertThat(actualObject).usingRecursiveComparison().isEqualTo(expectedObject);
        };
    }

    // ... rest of your code

    public static ResponseBodyMatchers responseBody() {
        return new ResponseBodyMatchers();
    }
}
