package com.softyorch.cursospring.form.app.validation;

import com.softyorch.cursospring.form.app.models.domain.UserDefault;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserDefaultValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDefault.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDefault user = (UserDefault) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.userDefault.username");

        if (!user.getId().matches("[0-9]{2}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}")) {
            errors.rejectValue("id", "Pattern.userDefault.id");
        }

    }
}
