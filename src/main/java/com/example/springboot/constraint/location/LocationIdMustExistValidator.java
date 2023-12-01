package com.example.springboot.constraint.location;

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
        boolean idExist = repository.existsById(id);
        if (!idExist) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Location with id '" + id + "' not found")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
