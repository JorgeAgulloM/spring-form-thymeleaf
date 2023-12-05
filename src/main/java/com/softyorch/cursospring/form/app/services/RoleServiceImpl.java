package com.softyorch.cursospring.form.app.services;

import com.softyorch.cursospring.form.app.models.domain.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    private final List<Role> roles;

    public RoleServiceImpl() {
        this.roles = new ArrayList<>();
        this.roles.add(new Role(1, "Administrador", "ROLE_ADMIN"));
        this.roles.add(new Role(2, "Usuario", "ROLE_USER"));
        this.roles.add(new Role(3, "Moderador", "ROLE_MODERATOR"));
    }

    @Override
    public List<Role> list() {
        return roles;
    }

    @Override
    public Role getById(Integer id) {
        for (Role role: roles) {
            if (role.getId().equals(id)) {
                return role;
            }
        }
        return null;
    }
}
