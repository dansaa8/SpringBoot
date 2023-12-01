package com.example.springboot.category;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Innehåller @RestBody som genererar JSON-data
@RequestMapping("api/categories")
@Validated
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoryView> getAll() {
        return service.getAllCategories();
    }

    @GetMapping("{id}")
    CategoryView getOne(@PathVariable int id) {
        return service.getOne(id);
    }

    @PostMapping
    public void addOne(@RequestBody @Valid CategoryReqBody category) {
        service.addCategory(category);
    }

}
