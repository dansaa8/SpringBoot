package com.example.springboot.constraint.coordinate;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LatitudeValidator implements ConstraintValidator<Latitude, Double> {

    @Override
    public void initialize(Latitude constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double latitude, ConstraintValidatorContext context) {
        return latitude != null && latitude >= -90 && latitude <= 90;
    }
}
