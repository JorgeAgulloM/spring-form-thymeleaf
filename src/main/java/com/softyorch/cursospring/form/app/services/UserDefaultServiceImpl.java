package com.softyorch.cursospring.form.app.services;

import com.softyorch.cursospring.form.app.models.domain.Country;
import com.softyorch.cursospring.form.app.models.domain.Role;
import com.softyorch.cursospring.form.app.models.domain.UserDefault;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDefaultServiceImpl implements IUserDefaultService {

    private List<UserDefault> list;

    public UserDefaultServiceImpl() {
        this.list = Arrays.asList(
                new UserDefault("1", "user1", "John", "Doe", "password1", "john.doe@example.com", 25, new Date(), new Country(1, "US", "United States"), Arrays.asList(new Role(1, "User", "ROLE_USER")), true, "Male", "value1"),
                new UserDefault("2", "user2", "Jane", "Doe", "password2", "jane.doe@example.com", 30, new Date(), new Country(2, "CA", "Canada"), Arrays.asList(new Role(2, "Admin", "ROLE_ADMIN")), true, "Female", "value2"),
                new UserDefault("3", "user3", "Bob", "Smith", "password3", "bob.smith@example.com", 22, new Date(), new Country(3, "UK", "United Kingdom"), Arrays.asList(new Role(3, "Moderator", "ROLE_MODERATOR")), false, "Male", "value3"),
                new UserDefault("4", "user4", "Alice", "Johnson", "password4", "alice.johnson@example.com", 28, new Date(), new Country(4, "AU", "Australia"), Arrays.asList(new Role(4, "User", "ROLE_USER")), true, "Female", "value4"),
                new UserDefault("5", "user5", "Charlie", "Brown", "password5", "charlie.brown@example.com", 35, new Date(), new Country(5, "FR", "France"), Arrays.asList(new Role(5, "Admin", "ROLE_ADMIN")), false, "Male", "value5"),
                new UserDefault("6", "user6", "Eva", "Williams", "password6", "eva.williams@example.com", 29, new Date(), new Country(6, "DE", "Germany"), Arrays.asList(new Role(6, "User", "ROLE_USER")), true, "Female", "value6"),
                new UserDefault("7", "user7", "George", "Miller", "password7", "george.miller@example.com", 26, new Date(), new Country(7, "IT", "Italy"), Arrays.asList(new Role(7, "Moderator", "ROLE_MODERATOR")), true, "Male", "value7"),
                new UserDefault("8", "user8", "Fiona", "Johnson", "password8", "fiona.johnson@example.com", 32, new Date(), new Country(8, "ES", "Spain"), Arrays.asList(new Role(8, "Admin", "ROLE_ADMIN")), false, "Female", "value8"),
                new UserDefault("9", "user9", "David", "Lee", "password9", "david.lee@example.com", 27, new Date(), new Country(9, "JP", "Japan"), Arrays.asList(new Role(9, "User", "ROLE_USER")), true, "Male", "value9"),
                new UserDefault("10", "user10", "Grace", "Martin", "password10", "grace.martin@example.com", 31, new Date(), new Country(10, "BR", "Brazil"), Arrays.asList(new Role(10, "Moderator", "ROLE_MODERATOR")), false, "Female", "value10")
        );
    }

    @Override
    public List<UserDefault> list() {
        return this.list;
    }

    @Override
    public UserDefault getById(String id) {
        for (UserDefault user: list) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Optional<UserDefault> getByIdOptional(String id) {
        UserDefault userDefault = this.getById(id);
        return Optional.ofNullable(userDefault);
    }
}
