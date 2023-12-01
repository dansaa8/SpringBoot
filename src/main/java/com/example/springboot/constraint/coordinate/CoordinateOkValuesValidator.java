package com.example.springboot.constraint.coordinate;

import com.example.springboot.location.Coordinate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CoordinateOkValuesValidator implements ConstraintValidator<CoordinateOkValues, Coordinate> {

    @Override
    public boolean isValid(Coordinate coordinate, ConstraintValidatorContext context) {
        double lon = coordinate.lon();
        double lat = coordinate.lat();

        return !(lat < -90) && !(lat > 90) && !(lon < -180) && !(lon > 180);
    }
}
