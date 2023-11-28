package com.example.springboot.category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryDTO> getAllCategories() {
        return repository.findCategoryDTOAllBy();
    }

    public Optional<CategoryDTO> getOneCategory(Integer id) {
        return repository.findCategoryDTOById(id);
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
