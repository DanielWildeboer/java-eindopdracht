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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


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

    //get all users in the application
    @RequestMapping(value = "api/user", method = RequestMethod.GET)
    public ResponseEntity users() {
        List<User> userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    //update a user
    @RequestMapping(method=RequestMethod.PUT, value="api/user/{id}")
    public ResponseEntity updateUser(@ModelAttribute User user, @PathVariable Long id){
        userService.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //remove user from the db
    @RequestMapping(value = "api/user/{Id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity removeUser(@PathVariable Long Id) {
        userService.delete(Id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "api/user/getToken/{userid}", method = RequestMethod.GET)
    public ResponseEntity createTokenTest(@PathVariable Long userid) {
        JwtTokenFactory factory = new JwtTokenFactory();
        User user = userService.findById(userid);
        user.setAuthToken(factory.createAccessJwtToken(user));
        userService.updateUser(userid, user);
        return new ResponseEntity<>(user.getAuthToken().getToken(), HttpStatus.OK);
    }

    @RequestMapping(value = "api/user/token/{userid}", method = RequestMethod.GET)
    public ResponseEntity getTokenTest(@PathVariable Long userid) {
        User currentUser = userService.findById(userid);
        User user = userService.findByAuthToken(currentUser.getAuthToken().getToken());
        return new ResponseEntity<>(user.getEmail(), HttpStatus.OK);
    }

}
