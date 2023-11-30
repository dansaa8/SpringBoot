package com.example.springboot.constraint;

import com.example.springboot.category.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryMustExistValidator implements ConstraintValidator<CategoryMustExist, String> {
    @Autowired
    private CategoryRepository repository;

    @Override
    public void initialize(CategoryMustExist constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return repository.existsByName(name);
    }
}
