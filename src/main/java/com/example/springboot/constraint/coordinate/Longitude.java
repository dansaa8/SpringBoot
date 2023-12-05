package com.example.springboot.constraint.coordinate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LongitudeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Longitude {
    String message() default "Invalid longitude value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
