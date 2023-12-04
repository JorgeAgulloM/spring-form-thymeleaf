package com.softyorch.cursospring.form.app.services;

import com.softyorch.cursospring.form.app.models.domain.Country;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CountryServiceImpl implements ICountryService {

    private final List<Country> list;

    public CountryServiceImpl() {
        list = Arrays.asList(
                new Country(1, "ES", "España"),
                new Country(2, "UK", "United Kingdom"),
                new Country(3, "FR", "Francia"),
                new Country(4, "CD", "Canadá"),
                new Country(5, "IT", "Italia")
        );
    }

    @Override
    public List<Country> list() {
        return list;
    }

    @Override
    public Country getById(Integer id) {
        for (Country country: list) {
            if (country.getId().equals(id)) {
                return country;
            }
        }
        return null;
    }
}
