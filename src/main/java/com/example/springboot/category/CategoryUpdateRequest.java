package com.example.springboot.category;

import com.example.springboot.constraint.category.CategoryMustNotExist;
import jakarta.validation.constraints.NotNull;

public record CategoryUpdateRequest(
        @NotNull @CategoryMustNotExist String name,
        @NotNull String symbol,
        @NotNull String description) {
}
