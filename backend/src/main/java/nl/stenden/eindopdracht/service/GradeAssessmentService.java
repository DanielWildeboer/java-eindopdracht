package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.GradeAssessment;

import java.util.Set;

public interface GradeAssessmentService {
    GradeAssessment findGradeAssessmentsByGroupIdAndStudentId(String groupId, String studentId);
    void addGradeAssessment(GradeAssessment gradeAssessment);
}


