package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.GradeAssessment;
import nl.stenden.eindopdracht.service.GradeAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class GradeAssessmentController {

    @Autowired
    private GradeAssessmentService gradeAssessmentService;

    //GET GRADEASSESSMENT BY USERID AND GROUPID
    @RequestMapping(value = "api/grade/assessment/{gid}/{sid}", method = RequestMethod.GET)
    public ResponseEntity getGradeAssessmentByGroupIdAndStudentId(@PathVariable String gid, @PathVariable String sid) {
        Set<GradeAssessment> gradeAssessment = gradeAssessmentService.findGradeAssessmentsByGroupIdAndStudentId(gid, sid);
        return new ResponseEntity<>(gradeAssessment, HttpStatus.FOUND);
    }

    //ADD GRADEASSESSMENT
    @RequestMapping(value = "api/grade/assessment", method = RequestMethod.POST)
    public ResponseEntity addGradeAssessment(@ModelAttribute GradeAssessment gradeAssessment) {
        gradeAssessmentService.addGradeAssessment(gradeAssessment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
