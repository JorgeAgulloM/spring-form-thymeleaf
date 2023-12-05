package com.softyorch.cursospring.form.app.controllers;

import com.softyorch.cursospring.form.app.editors.CountryPropertyEditor;
import com.softyorch.cursospring.form.app.editors.NameUppercaseEditor;
import com.softyorch.cursospring.form.app.models.domain.Country;
import com.softyorch.cursospring.form.app.models.domain.Role;
import com.softyorch.cursospring.form.app.models.domain.UserDefault;
import com.softyorch.cursospring.form.app.services.ICountryService;
import com.softyorch.cursospring.form.app.services.IRoleService;
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
import java.util.*;

@Controller
@SessionAttributes("userDefault")
public class FormController {

    @Autowired
    private UserDefaultValidation validator;

    @Autowired
    private ICountryService countryService;

    @Autowired
    private CountryPropertyEditor countryEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //binder.setValidator(validator); //Usando este método solo validará aquello que esté en la clase validadora, y no lo que se implemente en la entity.
        binder.addValidators(validator); //Usando este método añade la clase validadora a la validación implementada en la entity.

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false); //Tolerancia a la hora de reconocer los formatos introducidos. En true seria muy estricto.
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, false)); // forma global
        //binder.registerCustomEditor(Date.class, "bornDate", new CustomDateEditor(format, false)); //Forma expecífica para el campo

        binder.registerCustomEditor(String.class, "name", new NameUppercaseEditor()); //específico

        binder.registerCustomEditor(Country.class, "country", countryEditor); //específico
    }

    @ModelAttribute("roleList")
    public List<String> roleList() {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");
        roles.add("ROLE_MODERATOR");
        return roles;
    }

    @ModelAttribute("rolesMap")
    public Map<String, String> rolesMap() {
        Map<String, String> roles = new HashMap<>();
        roles.put("ROLE_ADMIN", "Administrador");
        roles.put("ROLE_USER", "Usuario");
        roles.put("ROLE_MODERATOR", "Moderador");
        return roles;
    }

    @ModelAttribute("countryList")
    public List<Country> countryList() {
        return countryService.list();
    }

    @ModelAttribute("countries")
    public List<String> countries() {
        return Arrays.asList("España", "United Kingdom", "Francia", "Canadá", "Italia");
    }

    @ModelAttribute("countriesMap")
    public Map<String, String> countryMap() {
        Map<String, String> countries = new HashMap<>();
        countries.put("ES", "España");
        countries.put("UK", "United Kingdom");
        countries.put("FR", "Francia");
        countries.put("CD", "Canadá");
        countries.put("IT", "Italia");

        return countries;
    }

    @GetMapping({"/form", "/"})
    public String form(Model model) {
        UserDefault user = new UserDefault();
        user.setId("12.345.678-K");
        user.setName("john");
        user.setSurname("Connor");
        user.setEmail("john@connor.com");
        user.setCount(6);
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
