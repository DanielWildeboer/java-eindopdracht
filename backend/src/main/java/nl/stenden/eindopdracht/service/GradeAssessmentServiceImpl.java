package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.GradeAssessment;
import nl.stenden.eindopdracht.repository.GradeAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GradeAssessmentServiceImpl implements  GradeAssessmentService {
    @Autowired
    private GradeAssessmentRepository gradeAssessmentRepository;

    @Override
    public GradeAssessment findGradeAssessmentsByGroupIdAndStudentId(String groupId, String studentId) {
        return gradeAssessmentRepository.findByGroup_idAndStudent_id(groupId, studentId);
    }

    @Override
    public void addGradeAssessment(GradeAssessment gradeAssessment) {
        gradeAssessmentRepository.save(gradeAssessment);
    }
}
