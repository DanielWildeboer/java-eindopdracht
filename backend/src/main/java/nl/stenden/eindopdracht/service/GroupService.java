package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.Group;

import java.util.List;
import java.util.Set;

public interface GroupService {
    Set<Group> findGroupsByUserName(String userName);
    Group findGroupByGroupName(String groupName);
}
