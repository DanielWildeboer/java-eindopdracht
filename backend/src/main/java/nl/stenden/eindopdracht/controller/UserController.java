package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.User;
import nl.stenden.eindopdracht.service.SecurityService;
import nl.stenden.eindopdracht.service.UserService;
import nl.stenden.eindopdracht.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity registration(@RequestBody User userForm){
        try{
            userService.save(userForm);
        } catch(Exception e) {
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<String>(e.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
