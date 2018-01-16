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
    @Qualifier("groupRepository")
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Override
    public ProjectGroup findGroupByGroupName(String groupName) {
        return null;
    }

    @Override
    public ProjectGroup findById(int Id) {
        return null;
    }

    @Override
    public Set<ProjectGroup> findGroupsByEmail(String email) {
        User user = userServiceImpl.findByEmail(email);
        return user.getGroups();
    }

    @Override
    public ProjectGroup findGroupById(String id) {
        return groupRepository.findById(id);
    }


}
