package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.ProjectGroup;
import nl.stenden.eindopdracht.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class GroupServiceImpl implements GroupService {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Override
    public ProjectGroup findGroupByGroupName(String groupName) {
        return null;
    }

    @Override
    public Set<ProjectGroup> findGroupsByEmail(String email) {
        User user = userServiceImpl.findByEmail(email);
        return user.getGroups();
    }
}
