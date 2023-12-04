package com.softyorch.cursospring.form.app.services;

import com.softyorch.cursospring.form.app.models.domain.Country;

import java.util.List;

public interface ICountryService {
    List<Country> list();
    Country getById(Integer id);
}
