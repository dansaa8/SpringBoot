package com.example.springboot;

public record CategoryNameSymbol(String name, String symbol) {

    public CategoryNameSymbol(Category category) {
        this(category.getName(), category.getSymbol());
    }

    public static CategoryNameSymbol of(Category category) {
        return new CategoryNameSymbol(category.getName(), category.getSymbol());
    }
}
