package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.GradeAssessment;
import nl.stenden.eindopdracht.repository.GradeAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GradeAssessmentServiceImpl implements  GradeAssessmentService {
    @Autowired
    private GradeAssessmentRepository gradeAssessmentRepository;

    @Override
    public Set<GradeAssessment> findGradeAssessmentsByGroupIdAndStudentId(String groupId, String studentId) {
        Set<GradeAssessment> gradeAssessments = new HashSet<GradeAssessment>();
        {
            gradeAssessmentRepository.findAllByGroup_idAndStudent_id(groupId, studentId).forEach(gradeAssessments::add);
            return gradeAssessments;
        }
    }

    @Override
    public void addGradeAssessment(GradeAssessment gradeAssessment) {
        gradeAssessmentRepository.save(gradeAssessment);
    }
}
