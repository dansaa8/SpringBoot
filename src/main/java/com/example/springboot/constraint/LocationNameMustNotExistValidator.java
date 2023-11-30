package com.example.springboot.constraint;

import com.example.springboot.location.LocationRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class LocationNameMustNotExistValidator implements ConstraintValidator<LocationNameMustNotExist, String> {
    @Autowired
    private LocationRepository repository;

    @Override
    public void initialize(LocationNameMustNotExist constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !repository.existsByName(name);
    }
}
