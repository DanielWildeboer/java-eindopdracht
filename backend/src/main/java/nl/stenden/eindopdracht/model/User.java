package nl.stenden.eindopdracht.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private String password;
    private Set<ProjectGroup> groupList;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId(){
        return id;
    }

    public void setId(int id) {
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

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @ManyToMany
    @JoinTable(name = "user_projectGroup", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "projectGroup_id"))
    public Set<ProjectGroup> getGroups() {
        return groupList;
    }

    public void setGroups(Set<ProjectGroup> groupList) {
        this.groupList = groupList;
    }
}