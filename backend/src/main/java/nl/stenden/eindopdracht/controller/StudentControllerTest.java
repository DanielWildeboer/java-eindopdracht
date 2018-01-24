package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;


class StudentControllerTest {

    @MockBean
    private StudentController controller;

    @Test
    void addStudent() {
        Student newStudent = new Student();
        newStudent.setEmail("nieuwe@student.nl");
        newStudent.setFirstName("Nieuwe");
        newStudent.setLastName("Student");
    }


    @Test
    void students() {
        addStudent();
        controller.students();
    }


}