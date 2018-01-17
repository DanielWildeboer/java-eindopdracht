package nl.stenden.eindopdracht.repository;

import nl.stenden.eindopdracht.model.ProjectGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("groupRepository")
public interface GroupRepository extends CrudRepository<ProjectGroup, Integer> {
}