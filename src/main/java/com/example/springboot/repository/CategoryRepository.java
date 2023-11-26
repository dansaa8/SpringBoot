package com.example.springboot.repository;

import com.example.springboot.entity.Category;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

// Innehåller vanliga crud-operationer. Automatgenereras, specialanpassad kod för MySQL, genom connectionstringen.
@Repository
public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
    Category findByNameIgnoreCase(String name);
}
