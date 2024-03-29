package com.softyorch.cursospring.form.app.controllers;

import com.softyorch.cursospring.form.app.editors.CountryPropertyEditor;
import com.softyorch.cursospring.form.app.editors.NameUppercaseEditor;
import com.softyorch.cursospring.form.app.editors.RoleEditor;
import com.softyorch.cursospring.form.app.errors.UserDefaultNotFoundException;
import com.softyorch.cursospring.form.app.models.domain.Country;
import com.softyorch.cursospring.form.app.models.domain.Role;
import com.softyorch.cursospring.form.app.models.domain.UserDefault;
import com.softyorch.cursospring.form.app.services.ICountryService;
import com.softyorch.cursospring.form.app.services.IRoleService;
import com.softyorch.cursospring.form.app.services.IUserDefaultService;
import com.softyorch.cursospring.form.app.validation.UserDefaultValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.softyorch.cursospring.form.app.interceptors.OpeningTimesInterceptor.MESSAGE;

@Controller
@SessionAttributes("userDefault")
public class FormController {

    @Value("${config.time.opening}")
    private int opening;

    @Value("${config.time.closed}")
    private int closed;

    @Autowired
    private UserDefaultValidation validator;

    @Autowired
    private ICountryService countryService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserDefaultService userDefaultService;

    @Autowired
    private CountryPropertyEditor countryEditor;

    @Autowired
    private RoleEditor roleEditor;

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

        binder.registerCustomEditor(Role.class, "roles", roleEditor);
    }

    @ModelAttribute("genderList")
    public List<String> gender() {
        return Arrays.asList("Hombre", "Mujer");
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

    @ModelAttribute("roleListEntity")
    public List<Role> roleListEntity() {
        return roleService.list();
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
        UserDefault user = new UserDefault(
                "12.345.678-K",
                "jonny",
                "john",
                "Connor",
                "12345",
                "john@connor.com",
                6,
                new Date(System.currentTimeMillis() - 1231212),
                new Country(1, "ES", "España"),
                List.of(new Role(2, "Usuario", "ROLE_USER"), new Role(3, "Moderador", "ROLE_MODERATOR")),
                true,
                "Hombre",
                "myValue12345"
        );
        model.addAttribute("title", "Formulario");
        model.addAttribute("userDefault", user);

        Integer v = Integer.parseInt("10x");
        //int v = 100/0; para generar el error de aritmética

        return "form";
    }

    @PostMapping("/form")
    public String input(@Valid UserDefault user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("title", "Resultado form");
            return "form";
        }

        return "redirect:/show";
    }

    @GetMapping("/show")
    public String show(
            @SessionAttribute(name = "userDefault", required = false) UserDefault user,
            Model model,
            SessionStatus status
    ) {

        if (user == null) return "redirect:/form";

        model.addAttribute("title", "Resultado");
        status.setComplete();

        return "result";
    }

    @GetMapping("/closed")
    public String closed(Model model) {
        StringBuilder message = new StringBuilder("Cerrado, por favor visítenos desde las ");
        message.append(opening);
        message.append(" hrs. y las ");
        message.append(closed);
        message.append(" hrs. Gracias.");
        model.addAttribute("title", "Fuera del horario de atención al cliente.");
        model.addAttribute(MESSAGE, message);

        return "closed";
    }

    @GetMapping("/user/{id}")
    public String user(@PathVariable Long id, Model model) {

        String idString = id.toString();
/*
        UserDefault userDefault = userDefaultService.getById(idString);

        if (userDefault == null)
            throw new UserDefaultNotFoundException(id.toString());
*/

        UserDefault userDefault = userDefaultService.getByIdOptional(idString).orElseThrow(() ->
                new UserDefaultNotFoundException(idString)
        );

        model.addAttribute("userDefault", userDefault);
        model.addAttribute("title", "Detalle usuario: ".concat(userDefault.getUsername()));

        return "user";
    }

}
