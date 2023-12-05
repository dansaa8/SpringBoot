package com.example.springboot.constraint.coordinate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CoordinateOkValuesValidator.class)
@Documented
public @interface CoordinateOkValues {
    String message() default "Invalid values";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
