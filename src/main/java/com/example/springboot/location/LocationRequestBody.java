package com.example.springboot.location;

import com.example.springboot.constraints.CategoryExistenceVerifier;
import jakarta.validation.constraints.NotNull;

public record LocationRequestBody(
        @NotNull String name,
        @NotNull boolean isPrivate,
        @NotNull String description,
        @NotNull @CategoryExistenceVerifier String categoryName,
        @NotNull CoordinateRequestBody coordinate
        ) {
}
