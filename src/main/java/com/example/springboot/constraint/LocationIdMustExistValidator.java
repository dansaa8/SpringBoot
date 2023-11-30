package com.example.springboot.constraint;

import com.example.springboot.location.LocationRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class LocationIdMustExistValidator implements ConstraintValidator<LocationIdMustExist, Integer> {
    @Autowired
    private LocationRepository repository;

    @Override
    public void initialize(LocationIdMustExist constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return repository.existsById(id);
    }
}
