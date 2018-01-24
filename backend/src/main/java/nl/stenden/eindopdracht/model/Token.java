package nl.stenden.eindopdracht.model;

import nl.stenden.eindopdracht.utility.RandomString;

import javax.persistence.*;

@Entity
@Table(name = "token")
public class Token {

    //Fields of token
    private int id;
    private String randomString;
    private String group_id;
    private String student_id;

    public Token() {

    }

    public Token(String group_id, String student_id) {
        this.randomString = new RandomString(30).toString();
        this.group_id = group_id;
        this.student_id = student_id;
    }

    //getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getTokenId(){
        return id;
    }

    public void setTokenId(int id) {
        this.id = id;
    }

    public String getGroupId() { return group_id; }

    public void setGroupId(String group_id) { this.group_id = group_id; }

    public String getStudentId() { return student_id; }

    public void setStudentId(String student_id) { this.student_id = student_id; }

    public String getRandomString() { return randomString; }

    public void setRandomString(String randomString) { this.randomString = randomString; }

}