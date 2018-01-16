package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.ProjectGroup;

import java.util.Set;

public interface GroupService {
    Set<ProjectGroup> findAllGroups();
    ProjectGroup findGroupById(String id);
    void addGroup(ProjectGroup group);
}



