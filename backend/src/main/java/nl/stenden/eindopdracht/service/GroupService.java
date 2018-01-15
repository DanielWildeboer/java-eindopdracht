package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.ProjectGroup;

import java.util.Set;

public interface GroupService {
    Set<ProjectGroup> findGroupsByUserName(String userName);
    ProjectGroup findGroupByGroupName(String groupName);
}
