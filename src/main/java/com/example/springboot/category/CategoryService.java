package com.example.springboot.category;

import com.example.springboot.exception.DuplicateEntryException;
import com.example.springboot.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryView> getAll() {
        return repository.findAllBy();
    }

    CategoryView getOne(int id) {
        return repository.findViewById(id).orElseThrow(() ->
                new NotFoundException("Category with id '" + id + "' not found"));
    }

    public void addCategory(CategoryUpdateRequest category) {
        if (repository.existsByNameIgnoreCase(category.name()))
            throw new DuplicateEntryException(category.name() + " already in use");

        Category categoryEntity = new Category();
        categoryEntity.setName(category.name());
        categoryEntity.setDescription(category.description());
        categoryEntity.setSymbol(category.symbol());

        repository.save(categoryEntity);
    }
}
