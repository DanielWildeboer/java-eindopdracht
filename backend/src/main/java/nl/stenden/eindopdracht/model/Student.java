package nl.stenden.eindopdracht.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private int student_number;

    @OneToMany
    private Set<Token> token;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirstName() { return first_name; }

    public void setFirstName(String firstName) { this.first_name = first_name; }

    public String getLastName() { return last_name; }

    public void setLastName(String last_name) { this.last_name = last_name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    @OneToMany
    @JoinTable(name = "student_token", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "token_id", referencedColumnName = "id"))
    public Set<Token> token() { return token; }

    public void token(Set<Token> token) { this.token = token; }

    public int getStudentNumber() { return student_number; }

    public void setStudentNumber(int studentNumber) { this.student_number = student_number; }
}
