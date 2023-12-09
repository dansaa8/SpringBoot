package com.example.springboot.category;

public record CategoryDTO(
        String name,
        String symbol,
        String description
) {
    public CategoryDTO(Category category) {
        this(
                category.getName(),
                category.getSymbol(),
                category.getDescription()
        );
    }
}
