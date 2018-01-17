package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.repository.StudentRepository;
import nl.stenden.eindopdracht.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Qualifier("studentRepository")
    @Autowired
    StudentRepository studentRepository;

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Student findByStudentNumber(int student_number) {
        return studentRepository.findByStudentNumber(student_number);
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findOne(id);
    }

    @Override
    public void updateStudent(int id, Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(int id) {
        studentRepository.delete(id);
    }
}
