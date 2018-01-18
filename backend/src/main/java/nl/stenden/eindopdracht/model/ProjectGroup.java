package nl.stenden.eindopdracht.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projectGroup")
public class ProjectGroup {

    @Id
    private int id;
    private String name;
    private String subject;
    private float grade;

    @OneToMany
    private Set<Student> students;

    public ProjectGroup(int id, String name, String subject, float grade)
    {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.grade = grade;
    }

    public ProjectGroup() { }

    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId(){
        return id;
    }

    public void setId(int id) {
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

    @OneToMany
    @JoinTable(name = "group_student", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    public Set<Student> getStudents() {
        return students;
    }
    public void setStudents(Set<Student> student) {
        this.students = student;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

}
