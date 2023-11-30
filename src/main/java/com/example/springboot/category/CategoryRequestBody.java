package com.example.springboot.category;

import com.example.springboot.constraint.CategoryMustNotExist;
import jakarta.validation.constraints.NotNull;

public record CategoryRequestBody (
        @NotNull @CategoryMustNotExist String name,
        @NotNull String symbol,
        @NotNull String description) {
}
