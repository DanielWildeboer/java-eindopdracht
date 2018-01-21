package nl.stenden.eindopdracht.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "gradeAssessment")
public class GradeAssessment {

    //fields of GradeAssessment
    private int id;
    private String group_id;
    private String student_id;
    private String user_id;
    private String sender_Student;
    private String receiver_Student;
    private float grade;
    private String description;

    //getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId(){ return id; }

    public void setId(int id) { this.id = id; }

    public String getGroupId() {
        return group_id;
    }

    public void setGroupId(String group_id) { this.group_id = group_id; }

    public String getStudentId() { return student_id; }

    public void setStudentId(String student_id) { this.student_id = student_id; }

    public String getUserId() { return user_id; }

    public void setUserId(String user_id) { this.user_id = user_id; }

    public String getSenderStudent() { return sender_Student; }

    public void setSenderStudent(String sender_Student) { this.sender_Student = sender_Student; }

    public String getReceiverStudent() { return receiver_Student; }

    public void setReceiverStudent(String receiver_Student) { this.receiver_Student = receiver_Student; }

    public float getGrade() { return grade; }

    public void setGrade(float grade) { this.grade = grade; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

}
