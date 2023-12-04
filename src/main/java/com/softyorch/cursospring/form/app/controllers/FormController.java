package com.softyorch.cursospring.form.app.controllers;

import com.softyorch.cursospring.form.app.models.domain.UserDefault;
import com.softyorch.cursospring.form.app.validation.UserDefaultValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@SessionAttributes("user")
public class FormController {

    @Autowired
    private UserDefaultValidation validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //binder.setValidator(validator); //Usando este método solo validará aquello que esté en la clase validadora, y no lo que se implemente en la entity.
        binder.addValidators(validator); //Usando este método añade la clase validadora a la validación implementada en la entity.

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false); //Tolerancia a la hora de reconocer los formatos introducidos. En true seria muy estricto.
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false));
    }

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
    public String input(
            @Valid @ModelAttribute("user") UserDefault user,
            BindingResult result,
            Model model,
            SessionStatus status
    ) {

        //validation.validate(user, result);

        model.addAttribute("title", "Resultado");

        if (result.hasErrors()) {

            return "form";
        }

        model.addAttribute("user", user);
        status.setComplete();

        return "result";
    }


}
