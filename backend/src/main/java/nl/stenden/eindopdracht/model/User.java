package nl.stenden.eindopdracht.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    private int id;
    private String first_name;
    private String last__name;
    private String email;
    private String token;
    private String password;
    private Set<Group> groupList;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last__name;
    }

    public void setLastName(String last__name) {
        this.last__name = last__name;
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
    public Set<Group> getGroups() {
        return groupList;
    }

    public void setGroups(Set<Group> groupList) {
        this.groupList = groupList;
    }
}