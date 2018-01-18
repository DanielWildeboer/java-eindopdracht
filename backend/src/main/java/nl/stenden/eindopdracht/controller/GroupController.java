package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.ProjectGroup;
import nl.stenden.eindopdracht.model.Student;
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
    @RequestMapping(method=RequestMethod.POST, value="/groups/addStudent/{groupId}")
    public void addStudent(@RequestBody Student student, @PathVariable int groupId){
        groupService.findGroupById(groupId).addStudent(student);
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
