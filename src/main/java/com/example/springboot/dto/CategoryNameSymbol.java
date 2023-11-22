package com.example.springboot.dto;

import com.example.springboot.entity.Category;

public record CategoryNameSymbol(
        String name,
        String symbol) {

    public CategoryNameSymbol(Category category) {
        this(category.getName(), category.getSymbol());
    }

    public static CategoryNameSymbol of(Category category) {
        return new CategoryNameSymbol(category.getName(), category.getSymbol());
    }
}
