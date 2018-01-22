package nl.stenden.eindopdracht.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    //fields of user
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    //getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }



}