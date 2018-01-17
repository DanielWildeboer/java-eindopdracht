package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);
    void save(Student student);
    List<Student> findAll();
    Student findByEmail(String email);
    Student findByStudentNumber(int student_number);
    Student findById(int id);
    void updateStudent(int id, Student student);
    void delete(int id);
}
