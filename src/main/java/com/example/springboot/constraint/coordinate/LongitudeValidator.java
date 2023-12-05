package com.example.springboot.constraint.coordinate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LongitudeValidator implements ConstraintValidator<Longitude, Double> {

    @Override
    public void initialize(Longitude constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double latitude, ConstraintValidatorContext context) {
        return latitude != null && latitude >= -180 && latitude <= 180;
    }
}