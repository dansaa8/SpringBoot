package com.example.springboot.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CategoryMustExistValidator.class)
@Documented
public @interface CategoryMustExist {
    String message() default "{The category provided doesn't exist}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}