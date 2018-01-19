//package nl.stenden.eindopdracht.controller;
//
//import nl.stenden.eindopdracht.model.GradeAssessment;
//import nl.stenden.eindopdracht.service.GradeAssessmentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class GradeAssessmentController {
//
//    @Autowired
//    private GradeAssessmentService gradeAssessmentService;
//
//    //GET GRADEASSESSMENT BY USERID AND GROUPID
//    @RequestMapping(value = "api/grade/assessment/{gid}/{sid}", method = RequestMethod.GET)
//    public ResponseEntity getGroupsByUserId(@PathVariable String gid, @PathVariable String sid) {
//       GradeAssessment gradeAssessment = gradeAssessmentService.findGradeAssessmentsByGroupIdAndStudentId(gid, sid);
//        return new ResponseEntity<>(gradeAssessment, HttpStatus.FOUND);
//    }
//
//    //ADD GRADEASSESSMENT
//}
