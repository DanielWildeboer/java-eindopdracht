package nl.stenden.eindopdracht.controller;

import java.security.Security;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message.RecipientType;
import org.bouncycastle.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class MailController {

    public static void main(String[] args) {

        Security.addProvider(new BouncyCastleProvider());


        final String username = "javatestlesley@gmail.com";
        final String password = "lesley123";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port", "587");



        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("iemand@gmail.com"));
            //hieronder kan  je de ontvanger opgeven, ik denk dat we een array van adressen kunnen gebruiken?
            message.setRecipients(RecipientType.TO, InternetAddress.parse("lesley.van.oostenrijk@student.stenden.com"));
            message.setSubject("Een test mail");
            message.setText("Beste Student," +
                "\n\n bezoek de onderstaande link om je medegroepleden te beoordelen" +
                "\n\n Met vriendelijke groet," +
                "\n\n Rob Smit");

            Transport.send(message);
            System.out.println("message sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
