package nl.stenden.eindopdracht.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "group")
public class Group {

    private long id;
    private String name;
    private String subject;
    private float grade;
    private Set<Student> students;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public float getGrade() { return grade; }

    public void setGrade(float grade) { this.grade = grade; }

    @ManyToMany
    @JoinTable(name = "group_student", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> roles) {
        this.students = roles;
    }
}
