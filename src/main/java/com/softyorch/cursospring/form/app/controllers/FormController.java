package com.softyorch.cursospring.form.app.controllers;

import com.softyorch.cursospring.form.app.models.domain.UserDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @GetMapping({"/form", "/"})
    public String form(Model model) {
        model.addAttribute("title", "Formulario");
        return "form";
    }

    @PostMapping("/form")
    public String input(
            Model model,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "email") String email
    ) {

        UserDefault user = new UserDefault();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        model.addAttribute("title", "Resultado");
        model.addAttribute("user", user);

        return "result";
    }


}
