package nl.stenden.eindopdracht.model;

import javax.persistence.*;

@Entity
@Table(name = "token")
public class Token {

    private long Id;
    private String groupId;
    private String studentId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getTokenId(){
        return Id;
    }

    public void setTokenId(Long Id) {
        this.Id = Id;
    }

    public String getGroupId() { return groupId; }

    public void setGroupId(String groupId) { this.groupId = groupId; }

    public String getStudentId() { return studentId; }

    public void setStudentId(String studentId) { this.studentId = studentId; }

}