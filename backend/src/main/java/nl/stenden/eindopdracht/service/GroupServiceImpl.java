package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.Group;
import nl.stenden.eindopdracht.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Set;

public class GroupServiceImpl implements GroupService {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Override
    public Group findGroupByGroupName(String groupName) {
        return null;
    }

    @Override
    public Set<Group> findGroupsByUserName(String userName) {
        User user = userServiceImpl.findByUsername(userName);
        return user.getGroups();
    }
}
