package nl.stenden.eindopdracht.model;

import javax.mail.Address;

public class Email {

    private Address from;
    private String to;
    private String subject;
    private String body;

    public Email(Address from, String to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public Address getFrom() { return from; }

    public void setFrom(Address from) { this.from = from; }

    public String getTo() { return to; }

    public void setTo(String to) { this.to = to; }

    public String getSubject() { return subject; }

    public void setSubject(String subject) { this.subject = subject; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }
}