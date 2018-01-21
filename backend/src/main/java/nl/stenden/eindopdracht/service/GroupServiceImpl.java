package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.ProjectGroup;
import nl.stenden.eindopdracht.model.Student;
import nl.stenden.eindopdracht.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.ManyToMany;
import java.util.*;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @ManyToMany
    private Set<Student> students;

    @Override
    public Set<ProjectGroup> findAllGroups() {
        Set<ProjectGroup> groups = new HashSet<ProjectGroup>();
        {
            groupRepository.findAll().forEach(groups::add);
            return groups;
        }
    }

    @Override
    public Set<ProjectGroup> findProjectGroupsByUserId(String id) {
        return groupRepository.findAllByUserId(id);
    }

    @Override
    public ProjectGroup findGroupById(int id) {
        return groupRepository.findOne(id);
    }

    @Override
    public void addGroup(ProjectGroup group) {
        groupRepository.save(group);
    }

    @Override
    public void updateGroup(int id, ProjectGroup group) {
        ProjectGroup currentGroup = findGroupById(id);
        if(group.getStudents() != null){
            currentGroup.setStudents(group.getStudents());
        }
        if(group.getStatus() != false) {
            currentGroup.setStatus(true);
        }
        if(group.getName() != null) {
            currentGroup.setName(group.getName());
        }
        if(group.getGrade() != 0) {
            currentGroup.setGrade(group.getGrade());
        }
        if(group.getSubject() != ""){
            currentGroup.setSubject(group.getSubject());
        }
        groupRepository.save(currentGroup);
    }

    @Override
    public void deleteGroup(int id) {
       groupRepository.delete(id);
    }

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }
}
