package com.example.springboot;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    List<CategoryNameSymbol> getAllCategories() {
        return repository.findAll().stream()
                .map(CategoryNameSymbol::new)
                .toList();
    }

    Optional<Category> getOneCategory(int id) {
        return repository.findById(id);
    }
}
