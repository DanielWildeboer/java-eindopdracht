package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.Token;
import nl.stenden.eindopdracht.service.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class TokenController {
    @Autowired
    private TokenServiceImpl tokenService;

    //ADD A TOKEN
    @RequestMapping(method= RequestMethod.POST, value="/token")
    public void deleteToken(@PathVariable Token token) {
        tokenService.addToken(token);
    }

    //FIND A TOKEN
    @RequestMapping(method= RequestMethod.GET, value="/token/{id}")
    public void findToken(@PathVariable int id) {
        tokenService.findTokenByIds(id);
    }

    //DELETE A TOKEN
    @RequestMapping(method= RequestMethod.DELETE, value="/token/{id}")
    public void deleteToken(@PathVariable int id) {
        tokenService.deleteToken(id);
    }
}
