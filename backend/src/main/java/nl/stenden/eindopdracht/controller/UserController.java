package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.User;
import nl.stenden.eindopdracht.service.SecurityService;
import nl.stenden.eindopdracht.service.UserService;
import nl.stenden.eindopdracht.service.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "api/register", method = RequestMethod.POST ,produces = "application/json")
    public ResponseEntity registration(@RequestBody User userForm, BindingResult bindingResult){

        HttpHeaders headers = new HttpHeaders();
        logger.info(userForm.getEmail(), userForm.getEmail());
            userValidator.validate(userForm, bindingResult);
            if(bindingResult.hasErrors()){
                for (ObjectError e: bindingResult.getAllErrors()) {
                    logger.info(e.toString());
                }
                return new ResponseEntity<>(bindingResult.getAllErrors().toString(),
                        headers, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            userService.save(userForm);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //get all users in the application
    @RequestMapping(value = "api/user", method = RequestMethod.GET)
    public List<User> users() {
        return userService.findAll();
    }

    //update a user
    @RequestMapping(method=RequestMethod.PUT, value="api/user/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id){
        userService.updateUser(id, user);
    }

    //remove user from the db
    @RequestMapping(value = "api/user/{Id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void  removeUser(@PathVariable Long Id) {
        userService.delete(Id);
    }






}
