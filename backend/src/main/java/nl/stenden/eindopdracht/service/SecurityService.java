package nl.stenden.eindopdracht.service;


public interface SecurityService {

    String findLoggedInUsername();

    void autologin(String email, String password);
}
