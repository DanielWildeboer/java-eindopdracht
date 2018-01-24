package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.User;

import java.util.List;

public interface UserService {

    //Interface of User functions
    void save(User user);
    List<User> findAll();
    User findByEmail(String email);
    User findById(Long id);
    void updateUser(Long id, User user);
    void delete(Long Id);
}
