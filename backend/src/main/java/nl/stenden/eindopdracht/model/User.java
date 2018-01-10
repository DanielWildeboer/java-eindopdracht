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

    @Column(name = "userName")
    @NotEmpty(message = "Please provide your user name")
    private String userName;

    @Column(name = "firstName")
    @NotEmpty(message = "Please provide your first name")
    private String firstName;

    @Column(name = "lastName")
    @NotEmpty(message = "Please provide your last name")
    private String lastName;

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

    public User(Long id, String userName, String firstName, String lastName, String email, String token, String password) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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