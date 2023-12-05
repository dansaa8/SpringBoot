package com.example.springboot.constraint.coordinate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LatitudeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Latitude {
    String message() default "Invalid latitude value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
