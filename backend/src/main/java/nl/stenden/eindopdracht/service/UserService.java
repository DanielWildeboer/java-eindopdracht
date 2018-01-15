package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.User;

public interface UserService {
    void save(User user);

    User findByEmail(String email);
}
