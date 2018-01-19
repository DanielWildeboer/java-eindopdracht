package nl.stenden.eindopdracht.repository;

import nl.stenden.eindopdracht.model.ProjectGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("GradeAssessmentRepository")
public interface GradeAssessmentRepository extends JpaRepository<ProjectGroup, Integer> {
}