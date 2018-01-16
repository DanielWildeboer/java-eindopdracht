package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> findAll();

    User findByEmail(String email);

    User findById(Long Id);

    void delete(Long Id);
}
