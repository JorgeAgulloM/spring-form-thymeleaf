package com.softyorch.cursospring.form.app.services;

import com.softyorch.cursospring.form.app.models.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> list();
    Role getById(Integer id);
}
