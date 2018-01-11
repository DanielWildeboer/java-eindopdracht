package nl.stenden.eindopdracht.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "gradeAssessment")
public class GradeAssessment {

    private long Id;
    private String groupId;
    private String studentId;
    private String userId;
    private String senderStudent;
    private String receiverStudent;
    private float grade;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId(){ return Id; }

    public void setId(long Id){ this.Id = Id; }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) { this.groupId = groupId; }

    public String getStudentId() { return studentId; }

    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getSenderStudent() { return senderStudent; }

    public void setSenderStudent(String senderStudent) { this.senderStudent = senderStudent; }

    public String getReceiverStudent() { return receiverStudent; }

    public void setReceiverStudent(String receiverStudent) { this.receiverStudent = receiverStudent; }

    public float getGrade() { return grade; }

    public void setGrade(float grade) { this.grade = grade; }

}
