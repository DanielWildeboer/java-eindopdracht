package nl.stenden.eindopdracht.repository;

import nl.stenden.eindopdracht.model.ProjectGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<ProjectGroup, Long> {

    ProjectGroup findById(String id);
}