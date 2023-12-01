package com.softyorch.cursospring.form.app.models.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDefault {

    private String id;
    @NotEmpty()
    @Size(min = 3, max = 20)
    private String username;
    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;
    @NotEmpty
    @Size(min = 3, max = 50)
    private String surname;
    @NotEmpty
    private String password;
    @NotEmpty
    @Email
    private String email;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
