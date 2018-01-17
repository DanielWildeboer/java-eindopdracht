package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.ProjectGroup;
import nl.stenden.eindopdracht.model.Student;

import java.util.List;
import java.util.Set;

public interface GroupService {
    Set<ProjectGroup> findAllGroups();
    ProjectGroup findGroupById(int id);
    void addGroup(ProjectGroup group);
    void updateGroup(int id, ProjectGroup group);
    void deleteGroup(int id);
    void addStudent(Student student);
}



