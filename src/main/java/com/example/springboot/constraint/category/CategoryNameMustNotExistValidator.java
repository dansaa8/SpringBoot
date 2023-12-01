package com.example.springboot.constraint.category;

import com.example.springboot.category.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryNameMustNotExistValidator implements ConstraintValidator<CategoryMustNotExist, String> {
    @Autowired
    private CategoryRepository repository;


    @Override
    public void initialize(CategoryMustNotExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        var foundCategory = repository.findByNameIgnoreCase(name);
        if (foundCategory.isPresent()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("'" + name + "' already exist")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
