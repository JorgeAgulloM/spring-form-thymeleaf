package com.softyorch.cursospring.form.app.editors;

import com.softyorch.cursospring.form.app.services.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class CountryPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private ICountryService countryService;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {
        try {
            Integer id = Integer.parseInt(idString);
            this.setValue(countryService.getById(id));
        } catch (NumberFormatException e) {
            setValue(null);
        }
    }
}
