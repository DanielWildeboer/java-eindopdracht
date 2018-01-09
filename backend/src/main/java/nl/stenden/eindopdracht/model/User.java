package nl.stenden.eindopdracht.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Please provide your name")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String email;

    @Column(name = "confirmation_token")
    public String token;

    @Column(name = "password")
    private String password;

    @Column(name = "passwordConfirm")
    private String passwordConfirm;

    public User() {

    }

    public User(Long id, String name, String email, String token, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.token = token;
        this.password = password;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
