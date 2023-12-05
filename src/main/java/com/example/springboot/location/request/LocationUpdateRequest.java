package com.example.springboot.location.request;

import com.example.springboot.constraint.category.CategoryMustExist;
import jakarta.validation.constraints.NotNull;

public record LocationUpdateRequest(
        @NotNull String name,
        @NotNull boolean isPrivate,
        @NotNull String description,
        @NotNull @CategoryMustExist String categoryName,
        @NotNull Coordinate coordinate
        ) {
}
