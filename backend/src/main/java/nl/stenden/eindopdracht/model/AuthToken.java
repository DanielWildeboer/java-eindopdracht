package nl.stenden.eindopdracht.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "auth_token")
public class AuthToken {

    private Long id;
    private String token;
    private User user;
    private Date expirationDate;

    public AuthToken(){

    }

    public AuthToken(String token, Date expirationDate){
        this.token = token;
        this.expirationDate = expirationDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @OneToOne(mappedBy = "authToken")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return expirationDate;
    }

    public void setDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
