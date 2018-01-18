package nl.stenden.eindopdracht.repository;

import nl.stenden.eindopdracht.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Integer>{
    Student findByEmail(String email);
    Student findByStudentNumber(int studentNumber);
}
