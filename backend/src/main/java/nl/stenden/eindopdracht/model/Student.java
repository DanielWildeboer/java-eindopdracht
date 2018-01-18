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
    private String firstName;
    private String lastName;
    private int studentNumber;

    @ManyToMany
    private Set<ProjectGroup> projectGroups;

    @OneToMany
    private Set<Token> token;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String last_name) { this.lastName = last_name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    @OneToMany
    @JoinTable(name = "student_token", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "token_id", referencedColumnName = "id"))
    public Set<Token> token() { return token; }

    public void token(Set<Token> token) { this.token = token; }

    public int getStudentNumber() { return studentNumber; }

    public void setStudentNumber(int studentNumber) { this.studentNumber = studentNumber; }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_group", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    public Set<ProjectGroup> getProjectGroups() {
        return projectGroups;
    }

    public void setProjectGroups(Set<ProjectGroup> projectGroups) {
        this.projectGroups = projectGroups;
    }
}
