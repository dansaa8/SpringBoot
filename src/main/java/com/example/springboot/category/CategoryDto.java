package com.example.springboot.category;

import com.example.springboot.category.Category;

public record CategoryDto(
        String name,
        String symbol) {

    public CategoryDto(Category category) {
        this(category.getName(), category.getSymbol());
    }

//    public static CategoryDto of(Category category) {
//        return new CategoryDto(category.getName(), category.getSymbol());
//    }
}
