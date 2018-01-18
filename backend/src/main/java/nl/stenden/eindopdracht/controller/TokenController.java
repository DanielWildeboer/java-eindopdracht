package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.Token;
import nl.stenden.eindopdracht.service.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TokenController {
    @Autowired
    private TokenServiceImpl tokenService;

    //ADD A TOKEN
    @RequestMapping(method= RequestMethod.POST, value="api/token/{groupId}/{studentId}")
    public void deleteToken(@PathVariable String groupId, @PathVariable String studentId) {
        tokenService.addToken(new Token(groupId, studentId));
    }

    //FIND A TOKEN
    @RequestMapping(method= RequestMethod.GET, value="api/token/{id}")
    public void findToken(@PathVariable int id) {
        tokenService.findTokenByIds(id);
    }

    //DELETE A TOKEN
    @RequestMapping(method= RequestMethod.DELETE, value="api/token/{id}")
    public void deleteToken(@PathVariable int id) {
        tokenService.deleteToken(id);
    }
}
