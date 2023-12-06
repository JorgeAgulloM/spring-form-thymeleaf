package com.softyorch.cursospring.form.app.errors;

import java.io.Serial;

public class UserDefaultNotFoundException extends RuntimeException {

    public UserDefaultNotFoundException(String id) {
        super("Usuario con id: ".concat(id).concat(" no existe en el sistema."));
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
