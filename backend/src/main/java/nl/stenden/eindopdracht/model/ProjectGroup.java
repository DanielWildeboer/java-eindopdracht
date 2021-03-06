package nl.stenden.eindopdracht.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projectGroup")
public class ProjectGroup {
    //fields of projectGroup
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userId;
    private String name;
    private String subject;
    private float grade;
    private boolean status;

    @ManyToMany
    private Set<Student> students;

    //constructor of projectGroup
    public ProjectGroup(int id, String userId, String name, String subject, float grade)
    {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.grade = grade;
    }

    public ProjectGroup() { }

    //getters and setters
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public float getGrade() { return grade; }

    public void setGrade(float grade) { this.grade = grade; }

    @ManyToMany(mappedBy = "groups")
    public Set<Student> getStudents() {
        return students;
    }
    public void setStudents(Set<Student> student) {
        this.students = student;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
