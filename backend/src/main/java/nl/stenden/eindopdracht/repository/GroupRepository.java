package nl.stenden.eindopdracht.repository;

import nl.stenden.eindopdracht.model.ProjectGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<ProjectGroup, Integer> {
    Set<ProjectGroup> findAllByUserId(String userId);
}