package com.example.springboot.constraints;

import com.example.springboot.location.CoordinateRequestBody;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CoordinateOkValuesValidator implements ConstraintValidator<CoordinateOkValues, CoordinateRequestBody> {


    @Override
    public boolean isValid(CoordinateRequestBody coordinate, ConstraintValidatorContext context) {
        double lon = coordinate.lon();
        double lat = coordinate.lat();

        return !(lat < -90) && !(lat > 90) && !(lon < -180) && !(lon > 180);
    }
}
