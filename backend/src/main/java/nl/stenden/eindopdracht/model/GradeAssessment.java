package nl.stenden.eindopdracht.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "gradeAssessment")
public class GradeAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "groupId", nullable = false, unique = true)
    private String groupId;

    @Column(name = "studentId", nullable = false, unique = true)
    private String studentId;

    public String getGroupId() {
        return groupId;
    }



    @Column(name = "userId", nullable = false, unique = true)
    private String userId;

    @Column(name = "senderStudent", nullable = false, unique = true)
    private String senderStudent;

    @Column(name = "receiverStudent", nullable = false, unique = true)
    private String receiverStudent;

    @Column(name = "grade", nullable = false, unique = true)
    private float grade;



    public GradeAssessment(){

    }

    public GradeAssessment(long id, String groupId, String studentId, String userId, String senderStudent, String receiverStudent, float grade) {
        this.id = id;
        this.groupId = groupId;
        this.studentId = studentId;
        this.userId = userId;
        this.senderStudent = senderStudent;
        this.receiverStudent = receiverStudent;
        this.grade = grade;
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
