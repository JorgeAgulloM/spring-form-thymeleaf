package com.softyorch.cursospring.form.app.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = IdRegexValidator.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface IdRegex {

    String message() default "Invalid Regex";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
