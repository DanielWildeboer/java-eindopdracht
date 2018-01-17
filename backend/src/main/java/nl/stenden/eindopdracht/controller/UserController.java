package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.User;
import nl.stenden.eindopdracht.service.SecurityService;
import nl.stenden.eindopdracht.service.UserService;
import nl.stenden.eindopdracht.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;


    @RequestMapping(value = "api/registration", method = RequestMethod.POST)
    public ResponseEntity registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult){

        HttpHeaders headers = new HttpHeaders();

            userValidator.validate(userForm, bindingResult);
            if(bindingResult.hasErrors()){
                return new ResponseEntity<String>(bindingResult.getAllErrors().toString(),
                        headers, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            userService.save(userForm);

        return new ResponseEntity<User>(userForm, HttpStatus.CREATED);
    }



    //get all users in the application
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<User> users() {
        return userService.findAll();
    }

    //remove user from the db
    @RequestMapping(value = "users/{Id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void  removeUser(@PathVariable Long Id) {
        userService.delete(Id);
    }






}
