package nl.stenden.eindopdracht.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "subject", nullable = false, unique = true)
    private String subject;

    @Column(name = "grade", nullable = false, unique = true)
    private float grade;

    private List<Student> studentList;

    
    public Group() {

    }

    public Group(long id, String name, String subject, float grade) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.grade = grade;

    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Student> getStudentList() { return studentList; }

    public void addStudent(Student student) { studentList.add(student); }

    public float getGrade() { return grade; }

    public void setGrade(float grade) { this.grade = grade; }


}
