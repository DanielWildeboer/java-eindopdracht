package nl.stenden.eindopdracht.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String email;

    @Column(name = "first_name")
    @NotEmpty(message = "Please provide your first name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Please provide your last name")
    private String lastName;

    @Column(name = "token")
    private String token;


    public Student() {

    }

    public Student(int id, String email, String firstName, String lastName, String token) {
            this.id = id;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.token = token;
    }


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String token() { return token; }

    public void token(String token) { this.token = token; }

}
