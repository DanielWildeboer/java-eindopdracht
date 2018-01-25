package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.Email;
import org.junit.Test;

import static org.junit.Assert.*;

public class MailControllerTest {

    @Test
    public void sentMail() {
        Email mail = new Email("test@test.test", "test@test.test", "Test", "Dit is een test");
    }
}