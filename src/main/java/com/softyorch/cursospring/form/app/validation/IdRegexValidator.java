package com.softyorch.cursospring.form.app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdRegexValidator implements ConstraintValidator<IdRegex, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("[0-9]{2}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}");
    }
}
