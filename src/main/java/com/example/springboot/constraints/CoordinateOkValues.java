package com.example.springboot.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CoordinateOkValuesValidator.class)
@Documented
public @interface CoordinateOkValues {
    String message() default "{Invalid values for the coordinate}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
