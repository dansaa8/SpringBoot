package com.example.springboot.location;

import jakarta.validation.constraints.NotNull;

public record LocationRequestBody(
        @NotNull String name,
        @NotNull boolean isPrivate,
        @NotNull String description,
        @NotNull String categoryName,
        @NotNull CoordinateRequestBody coordinate
        ) {
}
