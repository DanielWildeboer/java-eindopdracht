package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.ProjectGroup;

import java.util.Set;

public interface GroupService {
    Set<ProjectGroup> findGroupsByEmail(String email);
    ProjectGroup findGroupByGroupName(String groupName);
    ProjectGroup findById(int Id);
    ProjectGroup findGroupById(String id);
}



