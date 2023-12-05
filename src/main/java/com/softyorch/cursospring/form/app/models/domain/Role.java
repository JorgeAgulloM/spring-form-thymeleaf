package com.softyorch.cursospring.form.app.models.domain;

import java.util.Objects;

public class Role {

    private Integer id;
    private String name;
    private String role;

    public Role() {}

    public Role(Integer id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Role role1 = (Role) obj;
        return Objects.equals(id, role1.id) && Objects.equals(name, role1.name) && Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role);
    }
}
