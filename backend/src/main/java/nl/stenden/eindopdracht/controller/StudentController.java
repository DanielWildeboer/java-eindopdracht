package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.Student;
import nl.stenden.eindopdracht.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    //retrieve all students
    @RequestMapping(value = "api/student", method = RequestMethod.GET)
    public List<Student> students() { return studentService.findAll(); }

    //get student by id
    @RequestMapping(value = "api/student/{id}", method = RequestMethod.GET)
    public Student student(@PathVariable int id) { return studentService.findById(id);}

    //add new student
    @RequestMapping(value = "api/student", method = RequestMethod.POST)
    public Student addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return student;
    }

    //update student
    @RequestMapping(value = "api/student/{id}", method = RequestMethod.PUT)
    public void updateStudent(@RequestBody Student student, @PathVariable int id) { studentService.updateStudent(id, student); }

    //remove student
    @RequestMapping(value = "api/student/{id}", method = RequestMethod.DELETE)
    public void removeStudent(@RequestBody Student student, @PathVariable int id) { studentService.delete(id);}


}
