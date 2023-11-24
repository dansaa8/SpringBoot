package com.example.springboot.controller;

import com.example.springboot.dto.CategoryDto;
import com.example.springboot.requestbody.CategoryRequestBody;
import com.example.springboot.service.CategoryService;
import com.example.springboot.entity.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Innehåller @RestBody som genererar JSON-data
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoryDto> getAll() {
        return service.getAllCategories();
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getOne(@PathVariable int id) {
        var category = service.getOneCategory(id);
        if (category.isPresent())
            return ResponseEntity.ok().body(category.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    //Parametern vi lägger till kommer från requesten default json i SpringController
    public void addOne(@RequestBody CategoryRequestBody category) {
        System.out.println(category);
        service.addCategory(category);
    }

}
