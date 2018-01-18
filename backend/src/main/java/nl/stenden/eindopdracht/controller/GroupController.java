package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.ProjectGroup;
import nl.stenden.eindopdracht.model.Student;
import nl.stenden.eindopdracht.repository.GroupRepository;
import nl.stenden.eindopdracht.repository.StudentRepository;
import nl.stenden.eindopdracht.service.GroupServiceImpl;
import nl.stenden.eindopdracht.service.TokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class GroupController {

    @Autowired
    private GroupServiceImpl groupService;

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentRepository studentRepository;

    //GET ALL GROUPS
    @RequestMapping(value = "api/group", method = RequestMethod.GET)
    public Set<ProjectGroup> getGroups(){
        return groupService.findAllGroups();
    }

    //GET GROUP BY ID
    @RequestMapping(value = "api/group/{id}", method = RequestMethod.GET)
    public ProjectGroup getGroupById(@PathVariable int id){
        return groupService.findGroupById(id);
    }

    // POST A NEW GROUP AND RETURN THE ID OF THE GROUP WHICH CAN BE USED TO ADD USERS TO THE GROUP
    @RequestMapping(method=RequestMethod.POST, value="api/group")
    public int addGroup(@RequestBody ProjectGroup group){
        groupService.addGroup(group);
        return group.getId();
    }

    // ADD STUDENTS TO GROUP
    @RequestMapping(method=RequestMethod.POST, value="/api/group/addStudent/{groupId}/{studentId}")
    public void addStudent(@PathVariable int groupId, @PathVariable int studentId){
        ProjectGroup projectGroup = groupRepository.findOne(groupId);
        Student student = studentRepository.findOne(studentId);

        if (projectGroup != null) {
            projectGroup.getStudents().add(student);
            studentRepository.save(student);
        }
    }

    //UPDATE A GROUP
    @RequestMapping(method=RequestMethod.PUT, value="api/group/{id}")
    public void updateGroup(@RequestBody ProjectGroup group, @PathVariable int id){
        groupService.updateGroup(id, group);
    }

    //DELETE A GROUP
    @RequestMapping(method=RequestMethod.DELETE, value="api/group/{id}")
    public void deleteGroup(@PathVariable int id) {
        groupService.deleteGroup(id);
    }

}
