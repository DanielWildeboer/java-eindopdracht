package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.ProjectGroup;
import nl.stenden.eindopdracht.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Set<ProjectGroup> findAllGroups() {
        Set<ProjectGroup> groups = new HashSet<>();
        {
            groupRepository.findAll().forEach(groups::add);
            return groups;
        }
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
        groupRepository.save(group);
    }

    @Override
    public void deleteGroup(int id) {
       groupRepository.delete(id);
    }
}
