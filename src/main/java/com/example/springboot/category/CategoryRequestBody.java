package com.example.springboot.category;

import jakarta.validation.constraints.NotNull;

public record CategoryRequestBody (@NotNull String name, @NotNull String symbol, @NotNull String description) {

}
