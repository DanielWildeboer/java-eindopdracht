package nl.stenden.eindopdracht.controller;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Enumeration;
import java.util.Properties;


public class MailController {

    public static void main(String[] args) {

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

            //Add BouncyCastle content handlers to command map
            MailcapCommandMap mailcap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();

            mailcap.addMailcap("application/pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_signature");
            mailcap.addMailcap("application/pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_mime");
            mailcap.addMailcap("application/x-pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_signature");
            mailcap.addMailcap("application/x-pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_mime");
            mailcap.addMailcap("multipart/signed;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.multipart_signed");

            CommandMap.setDefaultCommandMap(mailcap);

            Security.addProvider(new BouncyCastleProvider());

            KeyStore keyStore = KeyStore.getInstance("JKS");

            //Provide location of Java Keystore and password for access
            keyStore.load(new FileInputStream("..//..//resources//cert.jks"), "lesley123".toCharArray());


            //Find the first legit alias in the keystore and use it
            Enumeration<String> es = keyStore.aliases();
            String alias = "";
            while (es.hasMoreElements()) {
                alias = (String) es.nextElement();

                //Does alias refer to a private key? Assign true/false to isAlias & evaluate
                if (isAlias = keyStore.isKeyEntry(alias)) {
                    break;
                }
            }
            if (isAlias) {
                KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, new KeyStore.PasswordProtection("lesley123".toCharArray()));

            }


            //SEND MESSAGE
            Transport.send(message);
            System.out.println("message sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        }
    }




}
