package com.example.springboot.category;

import com.example.springboot.category.Category;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

// Innehåller vanliga crud-operationer. Automatgenereras, specialanpassad kod för MySQL, genom connectionstringen.
@Repository
public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
    Category findByNameIgnoreCase(String name);
}
