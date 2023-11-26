package com.example.springboot.constraints;

import com.example.springboot.category.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryExistenceValidator implements ConstraintValidator<CategoryExistenceVerifier, String> {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public void initialize(CategoryExistenceVerifier constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        var foundCategory = categoryRepository.findByNameIgnoreCase(s);
        return foundCategory.isPresent();
    }
}
