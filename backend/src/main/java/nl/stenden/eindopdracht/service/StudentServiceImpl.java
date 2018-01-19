package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.repository.StudentRepository;
import nl.stenden.eindopdracht.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        Student foundStudent = findById(id);
        if(foundStudent != null) {
            foundStudent.setFirstName(student.getFirstName());
            foundStudent.setLastName(student.getLastName());
            foundStudent.setEmail(student.getEmail());
            foundStudent.setStudentNumber(student.getStudentNumber());
        }
    }

    @Override
    public void delete(int id) {
        studentRepository.delete(id);
    }
}
