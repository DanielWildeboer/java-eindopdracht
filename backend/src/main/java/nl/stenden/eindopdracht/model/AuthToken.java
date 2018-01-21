package nl.stenden.eindopdracht.model;

import javax.persistence.*;

@Entity
@Table(name = "authToken")
public class AuthToken {
    private Long id;
    private String token;
    private User user;

    public AuthToken(String token, User user){
        this.token = token;
        this.user = user;
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
        token = token;
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
