package com.example.springboot.constraint.category;

import com.example.springboot.category.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryMustNotExistValidator implements ConstraintValidator<CategoryMustNotExist, String> {
    @Autowired
    private CategoryRepository repository;


    @Override
    public void initialize(CategoryMustNotExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        var foundCategory = repository.findByNameIgnoreCase(s);
        if (foundCategory.isPresent()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("' " + "' already exist")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
