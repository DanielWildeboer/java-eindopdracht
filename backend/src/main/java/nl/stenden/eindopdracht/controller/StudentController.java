package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.Student;
import nl.stenden.eindopdracht.service.StudentService;
import nl.stenden.eindopdracht.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class StudentController {

    private StudentServiceImpl studentService;

    //retrieve all students
    @RequestMapping(value = "api/student", method = RequestMethod.GET)
    public List<Student> students() { return studentService.findAll(); }

    //add new student
    @RequestMapping(value = "api/student", method = RequestMethod.POST)
    public int addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return student.getId();
    }

    //update student
    @RequestMapping(value = "api/student/{id}", method = RequestMethod.PUT)
    public void updateStudent(@RequestBody Student student, @PathVariable int id) { studentService.updateStudent(id, student); }

    //remove student
    @RequestMapping(value = "api/student/{id}", method = RequestMethod.DELETE)
    public void removeStudent(@RequestBody Student student, @PathVariable int id) { studentService.delete(id);}


}
