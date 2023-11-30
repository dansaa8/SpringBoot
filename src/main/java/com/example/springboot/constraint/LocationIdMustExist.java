package com.example.springboot.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = LocationIdMustExistValidator.class)
@Documented
public @interface LocationIdMustExist {
    String message() default "{There is no Location with that ID}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
