package com.example.springboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Innehåller @RestBody som genererar JSON-data
@RequestMapping("api/categories")
public class CategoryController {

    private CategoryService service;

    public CategoryController(CategoryService categoryService) {
        this.service = categoryService;
    }

    @GetMapping
    public List<CategoryNameSymbol> getAll() {
        return service.getAllCategories();
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getOne(@PathVariable int id) {
        var category = service.getOneCategory(id);
        if (category.isPresent())
            return ResponseEntity.ok().body(category.get());
        return ResponseEntity.notFound().build();
    }
}
