package nl.stenden.eindopdracht.model;

import javax.persistence.*;

@Entity
@Table(name = "token")
public class Token {

    private int id;
    private String group_id;
    private String student_id;

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

}