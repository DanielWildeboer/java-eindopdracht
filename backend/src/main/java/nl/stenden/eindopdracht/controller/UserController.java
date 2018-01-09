package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.service.EmailService;
import nl.stenden.eindopdracht.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserService userService;
    private SecurityService securityService;
    private UserValidator userValidator;

    @Autowired
    public UserController(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService, EmailService emailService){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.emailService = emailService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ResponseEntity registerUser(@RequestBody MultiValueMap<String,String> formData){
//        User userExists = userService.findByEmail(formData.em)
    }

}
