package com.softyorch.cursospring.form.app.models.domain;

import com.softyorch.cursospring.form.app.validation.IdRegex;
import com.softyorch.cursospring.form.app.validation.Required;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserDefault {

    //@Pattern(regexp = "[0-9]{2}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}")
    @Required
    @IdRegex
    private String id;
    //@NotEmpty
    //@Size(min = 3, max = 20)
    private String username;
    @Required
    @Size(min = 3, max = 50)
    private String name;
    @Required
    @Size(min = 3, max = 50)
    private String surname;
    @Required
    private String password;
    @Required
    @Email
    private String email;

    @Min(5)
    @Max(5000)
    @NotNull
    private Integer count;

    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date bornDate;

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

    public Integer getCount() { return count; }

    public void setCount(Integer count) { this.count = count; }

    public Date getBornDate() { return bornDate; }

    public void setBornDate(Date bornDate) { this.bornDate = bornDate; }
}
