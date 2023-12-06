package com.softyorch.cursospring.form.app.services;

import com.softyorch.cursospring.form.app.models.domain.UserDefault;

import java.util.List;
import java.util.Optional;

public interface IUserDefaultService {
    List<UserDefault> list();
    UserDefault getById(String id);
    Optional<UserDefault> getByIdOptional(String id);
}
