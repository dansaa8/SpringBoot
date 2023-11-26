package com.example.springboot.requestbody;

import jakarta.validation.constraints.NotNull;

public record CategoryRequestBody (@NotNull String name, @NotNull String symbol, @NotNull String description) {

}
