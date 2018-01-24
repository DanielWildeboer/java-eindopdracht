package nl.stenden.eindopdracht.service;
import nl.stenden.eindopdracht.model.Email;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.smime.SMIMECapabilitiesAttribute;
import org.bouncycastle.asn1.smime.SMIMECapability;
import org.bouncycastle.asn1.smime.SMIMECapabilityVector;
import org.bouncycastle.asn1.smime.SMIMEEncryptionKeyPreferenceAttribute;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.mail.smime.SMIMEException;
import org.bouncycastle.mail.smime.SMIMESignedGenerator;
import org.springframework.stereotype.Service;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.NoSuchProviderException;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(final Email email) {
        //login details for mailtrap
        final String username = "29754260239579";
        final String password = "b357a9e2bdfc0a";

        //fiels required for the signing
        boolean isAlias = false;
        String alias = "";

        //add smtp details of mailtrap server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host","smtp.mailtrap.io");
        props.put("mail.smtp.port", "2525");

        //get instance of the session
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            //set message parameters (before signing)
            Message message = new MimeMessage(session);
            message.addFrom(InternetAddress.parse(email.getFrom()));
            message.setSubject(email.getSubject());
            message.setText(email.getBody());


            //Add BouncyCastle content handlers to command map
            MailcapCommandMap mailcap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            mailcap.addMailcap("application/pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_signature");
            mailcap.addMailcap("application/pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.pkcs7_mime");
            mailcap.addMailcap("application/x-pkcs7-signature;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_signature");
            mailcap.addMailcap("application/x-pkcs7-mime;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.x_pkcs7_mime");
            mailcap.addMailcap("multipart/signed;; x-java-content-handler=org.bouncycastle.mail.smime.handlers.multipart_signed");
            CommandMap.setDefaultCommandMap(mailcap);

            //add BouncyCastle as security provider to enable signing of emails
            Security.addProvider(new BouncyCastleProvider());

            //definition of the keystore type
            KeyStore keyStore = KeyStore.getInstance("JKS");

            //Relative path to the certification
            File file = new File("src/main/resources/cert.jks");
            String absolutePath = file.getAbsolutePath();

            //Provide location of Java Keystore and password for access
            keyStore.load(new FileInputStream(absolutePath),
                    "lesley123".toCharArray());

            //Find the first legit alias in the keystore and use it
            Enumeration<String> es = keyStore.aliases();

            while (es.hasMoreElements()) {
                alias = es.nextElement();

                //Does alias refer to a private key? Assign true/false to isAlias & evaluate
                if (isAlias = keyStore.isKeyEntry(alias)) {
                    break;
                }
            }
            if (isAlias) {
                KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, new KeyStore.PasswordProtection("lesley123".toCharArray()));
                PrivateKey myPrivateKey = pkEntry.getPrivateKey();

                // Load certificate chain
                Certificate[] chain = keyStore.getCertificateChain(alias);

                // Create the SMIMESignedGenerator
                SMIMECapabilityVector capabilities = new SMIMECapabilityVector();
                capabilities.addCapability(SMIMECapability.dES_EDE3_CBC);
                capabilities.addCapability(SMIMECapability.rC2_CBC, 128);
                capabilities.addCapability(SMIMECapability.dES_CBC);
                capabilities.addCapability(SMIMECapability.aES256_CBC);

                ASN1EncodableVector attributes = new ASN1EncodableVector();
                attributes.add(new SMIMEEncryptionKeyPreferenceAttribute(
                        new IssuerAndSerialNumber(
                                new X500Name(((X509Certificate) chain[0])
                                        .getIssuerDN().getName()),
                                ((X509Certificate) chain[0]).getSerialNumber())));
                attributes.add(new SMIMECapabilitiesAttribute(capabilities));

                SMIMESignedGenerator signer = new SMIMESignedGenerator();

                signer.addSigner(myPrivateKey,
                        (X509Certificate) chain[0],
                        "DSA".equals(myPrivateKey.getAlgorithm()) ? SMIMESignedGenerator.DIGEST_SHA1 : SMIMESignedGenerator.DIGEST_MD5,
                        new AttributeTable(attributes),null);

                //Add list of certs to the generator
                List certList = new ArrayList();
                certList.add(chain[0]);
                CertStore certs = CertStore.getInstance("Collection",
                        new CollectionCertStoreParameters(certList), "BC");
                signer.addCertificatesAndCRLs(certs);

                // Sign the message
                MimeMultipart mm = signer.generate((MimeMessage) message, "BC");
                MimeMessage signedMessage = new MimeMessage(session);

                // Set all original MIME headers in the signed message (does not work)
                //Enumeration headers = message.getAllHeaders();
                //while (headers.hasMoreElements()) {
                //      signedMessage.addHeaderLine((String) headers.nextElement());
                //  }

                signedMessage.addFrom(InternetAddress.parse(email.getFrom()));
                signedMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
                // Set the content of the signed message
                signedMessage.setContent(mm);
                signedMessage.saveChanges();

                // Send the message
                Transport.send(signedMessage);
                System.out.println("message sent");
            }
        }
        catch (NoSuchProviderException e) { e.printStackTrace(); }
        catch (UnrecoverableEntryException e) { e.printStackTrace(); }
        catch (KeyStoreException e) { e.printStackTrace(); }
        catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (AddressException e) { e.printStackTrace(); }
        catch (MessagingException e) { e.printStackTrace(); }
        catch (CertificateException e) { e.printStackTrace(); }
        catch (InvalidAlgorithmParameterException e) { e.printStackTrace(); }
        catch (CertStoreException e) { e.printStackTrace(); }
        catch (SMIMEException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }
}
