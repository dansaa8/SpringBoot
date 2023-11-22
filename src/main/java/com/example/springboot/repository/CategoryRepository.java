package com.example.springboot.repository;

import com.example.springboot.entity.Category;
import org.springframework.data.repository.ListCrudRepository;

// Innehåller vanliga crud-operationer. Automatgenereras, specialanpassad kod för MySQL, genom connectionstringen.
public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
}
