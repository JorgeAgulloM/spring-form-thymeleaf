package com.softyorch.cursospring.form.app.editors;

import com.softyorch.cursospring.form.app.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class RoleEditor extends PropertyEditorSupport {

    @Autowired
    private IRoleService roleService;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {
        try {
            Integer id = Integer.parseInt(idString);
            setValue(roleService.getById(id));
        } catch (NumberFormatException e) {
            setValue(null);
        }
    }
}
