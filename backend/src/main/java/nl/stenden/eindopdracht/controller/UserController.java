package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.AuthToken;
import nl.stenden.eindopdracht.model.User;
import nl.stenden.eindopdracht.service.AuthTokenService;
import nl.stenden.eindopdracht.service.SecurityService;
import nl.stenden.eindopdracht.service.UserService;
import nl.stenden.eindopdracht.service.UserValidator;
import nl.stenden.eindopdracht.utility.JwtTokenFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.ErrorType;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class UserController {

    //object userService
    @Autowired
    private UserService userService;

    //object userValidator
    @Autowired
    private UserValidator userValidator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //object logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //register a user
    @RequestMapping(value = "api/register", method = RequestMethod.POST ,produces = "application/json")
    public ResponseEntity registration(@RequestBody User userForm, BindingResult bindingResult){
        HttpHeaders headers = new HttpHeaders();
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

    //get all users in the db
    @RequestMapping(value = "api/user", method = RequestMethod.GET)
    public ResponseEntity users() {
        List<User> userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


    //get a single user from db
    @RequestMapping(value = "api/user/{id}", method = RequestMethod.GET)
    public ResponseEntity getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //update an existing user in db
    @RequestMapping(method=RequestMethod.PATCH, value="api/user/{userid}")
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable("userid") Long userid){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.updateUser(userid, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //remove user from the db
    @RequestMapping(value = "api/user/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity removeUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

