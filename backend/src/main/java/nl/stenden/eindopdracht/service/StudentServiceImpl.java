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

    //add new student
    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    //save student information / create new or update existing
    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    //find all students
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    //find a student by e-mail
    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    //find a student by studentnumber
    @Override
    public Student findByStudentNumber(int student_number) {
        return studentRepository.findByStudentNumber(student_number);
    }

    //find a student by student id
    @Override
    public Student findById(int id) {
        return studentRepository.findOne(id);
    }

    //update an existing student
    @Override
    public void updateStudent(int id, Student student) {
        Student foundStudent = findById(id);
        if(student.getEmail() != null) {
            foundStudent.setEmail(student.getEmail());
        }
        if(student.getFirstName() != null) {
            foundStudent.setFirstName(student.getFirstName());
        }
        if(student.getLastName() != null) {
            foundStudent.setLastName(student.getLastName());
        }
        if(student.getStudentNumber() != 0){
            foundStudent.setStudentNumber(student.getStudentNumber());
        }
        studentRepository.save(foundStudent);
    }

    //remove student
    @Override
    public void delete(int id) {
        studentRepository.delete(id);
    }
}
