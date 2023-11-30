package com.example.springboot.category;

import java.io.Serializable;

public record CategoryDTO(String name, String description, String symbol) implements Serializable
{
    public CategoryDTO(Category c) {
        this(c.getName(), c.getDescription(), c.getSymbol());
    }
}
