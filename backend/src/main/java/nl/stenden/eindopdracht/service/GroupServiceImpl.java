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

    //get all groups
    @Override
    public Set<ProjectGroup> findAllGroups() {
        Set<ProjectGroup> groups = new HashSet<ProjectGroup>();
        {
            groupRepository.findAll().forEach(groups::add);
            return groups;
        }
    }

    //get group by User id
    @Override
    public Set<ProjectGroup> findProjectGroupsByUserId(String id) {
        return groupRepository.findAllByUserId(id);
    }

    //get group by Group id
    @Override
    public ProjectGroup findGroupById(int id) {
        return groupRepository.findOne(id);
    }

    //add new group
    @Override
    public void addGroup(ProjectGroup group) {
        groupRepository.save(group);
    }

    //update an existing group
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

    //remove a group
    @Override
    public void deleteGroup(int id) {
       groupRepository.delete(id);
    }

    //add student to group
    @Override
    public void addStudent(Student student) {
        students.add(student);
    }
}
