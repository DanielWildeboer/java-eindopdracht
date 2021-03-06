package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.Student;
import nl.stenden.eindopdracht.service.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    //object logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //retrieve all students
    @RequestMapping(value = "api/student", method = RequestMethod.GET)
    public ResponseEntity students() {
        List<Student> studentList = studentService.findAll();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    //get student by id
    @RequestMapping(value = "api/student/{id}", method = RequestMethod.GET)
    public ResponseEntity student(@PathVariable int id) {
        Student student = studentService.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //add new student
    @RequestMapping(value = "api/student", method = RequestMethod.POST)
    public ResponseEntity addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return new ResponseEntity<>(student.getId(), HttpStatus.CREATED);
    }

    //update student
    @RequestMapping(value = "api/student/{studentid}", method = RequestMethod.PATCH)
    public ResponseEntity updateStudent(@RequestBody Student student, @PathVariable int studentid) {
        logger.info(student.getEmail() + " " + student.getFirstName()+ " " + student.getLastName()+ " " + student.getStudentNumber());
        studentService.updateStudent(studentid, student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //remove student
    @RequestMapping(value = "api/student/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeStudent(@PathVariable int id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
