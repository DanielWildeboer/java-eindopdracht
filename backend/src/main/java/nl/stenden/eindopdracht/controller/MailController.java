package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.Email;
import nl.stenden.eindopdracht.model.Token;
import nl.stenden.eindopdracht.service.EmailService;
import nl.stenden.eindopdracht.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class MailController {

    //Object EmailService
    @Autowired
    private EmailService emailService;

    //Object Tokenservice
    @Autowired
    private TokenService tokenService;

    //GET ALL GROUPS
    @RequestMapping(value = "/sentMail/{toEmail}/{fromEmail}/{tokenId}", method = RequestMethod.POST)
    public void sentMail(@PathVariable String toEmail, @PathVariable String fromEmail, @PathVariable int tokenId) {
        Token token = tokenService.findTokenById(tokenId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost/JavaEindOpdracht/java-eindopdracht/frontend/src/grading/grade.html");

        builder.queryParam("studentId", token.getStudentId());
        builder.queryParam("groupId", token.getGroupId());
        builder.queryParam("token", token.getRandomString());

        //Build and send an email to a student
        Email email = new Email(fromEmail, toEmail, "Beoordeling", "Beste Student," +
                "\n\n bezoek de onderstaande link om je medegroepleden te beoordelen" +
                "\n\n" + builder.build().toString() +
                "\n\n Met vriendelijke groet");
        emailService.sendEmail(email);

    }
}
