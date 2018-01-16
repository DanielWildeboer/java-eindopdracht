package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.ProjectGroup;
import nl.stenden.eindopdracht.model.User;
import nl.stenden.eindopdracht.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class GroupServiceImpl implements GroupService {


    @Override
    public Set<ProjectGroup> findAllGroups() {
        return null; //return all the groups in database
    }

    @Override
    public ProjectGroup findGroupById(String id) {
        return null; //return group by id
    }

    @Override
    public void addGroup(ProjectGroup group) {

    }
}
