package com.softyorch.cursospring.form.app.services;

import com.softyorch.cursospring.form.app.models.domain.UserDefault;

import java.util.List;

public interface IUserDefaultService {
    List<UserDefault> list();
    UserDefault getById(String id);
}
