package com.softyorch.cursospring.form.app.controllers;

import com.softyorch.cursospring.form.app.models.domain.UserDefault;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class FormController {

    @GetMapping({"/form", "/"})
    public String form(Model model) {
        UserDefault user = new UserDefault();
        model.addAttribute("title", "Formulario");
        model.addAttribute("userDefault", user);
        return "form";
    }

    @PostMapping("/form")
    public String input(@Valid UserDefault user, BindingResult result, Model model) {

        model.addAttribute("title", "Resultado");

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            result.getFieldErrors().forEach(err ->
                    errors.put(
                            err.getField(),
                            "El campo "
                                    .concat(err.getField()).concat(" ")
                                    .concat(Objects.requireNonNull(err.getDefaultMessage())))
            );
            model.addAttribute("error", errors);

            return "form";
        }

        model.addAttribute("user", user);

        return "result";
    }


}
