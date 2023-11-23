package com.example.springboot.service;

import com.example.springboot.dto.CategoryDto;
import com.example.springboot.entity.Category;
import com.example.springboot.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryDto> getAllCategories() {
        return repository.findAll().stream()
                .map(CategoryDto::new)
                .toList();
    }

    public Optional<Category> getOneCategory(int id) {
        return repository.findById(id);
    }
}
