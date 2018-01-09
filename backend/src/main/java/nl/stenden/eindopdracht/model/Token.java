package nl.stenden.eindopdracht.model;

import javax.persistence.*;

@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long tokenId;

    @Column(name = "groupId", nullable = false, unique = true)
    private String groupId;

    @Column(name = "studentId", nullable = false, unique = true)
    private String studentId;

    public Token() {
    }

    public Token(long tokenId, String groupId, String studentId) {
        this.tokenId = tokenId;
        this.groupId = groupId;
        this.studentId = studentId;
    }

    public Long getTokenId(){
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getGroupId() { return groupId; }

    public void setGroupId(String groupId) { this.groupId = groupId; }

    public String getStudentId() { return studentId; }

    public void setStudentId(String studentId) { this.studentId = studentId; }




}