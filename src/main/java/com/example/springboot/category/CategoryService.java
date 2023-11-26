package com.example.springboot.category;

import com.example.springboot.category.CategoryDto;
import com.example.springboot.category.Category;
import com.example.springboot.category.CategoryRepository;
import com.example.springboot.category.CategoryRequestBody;
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

    public void addCategory(CategoryRequestBody category) {
        //Skapa entitetsobjekt
        Category categoryEntity = new Category();
        categoryEntity.setName(category.name());
        categoryEntity.setDescription(category.description());
        categoryEntity.setSymbol(category.symbol());

        repository.save(categoryEntity);
    }
}
