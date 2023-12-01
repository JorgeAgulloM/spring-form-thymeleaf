package com.softyorch.cursospring.form.app.controllers;

import com.softyorch.cursospring.form.app.models.domain.UserDefault;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {

    @GetMapping({"/form", "/"})
    public String form(Model model) {
        UserDefault user = new UserDefault();
        user.setId("123.456.789-K");
        user.setName("john");
        user.setSurname("Connor");
        model.addAttribute("title", "Formulario");
        model.addAttribute("user", user);
        return "form";
    }

    @PostMapping("/form")
    public String input(@Valid @ModelAttribute("user") UserDefault user, BindingResult result, Model model) {

        model.addAttribute("title", "Resultado");

        if (result.hasErrors()) {

            return "form";
        }

        model.addAttribute("user", user);

        return "result";
    }


}
