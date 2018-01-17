package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.ProjectGroup;
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
    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public Set<ProjectGroup> getGroups(){
        return groupService.findAllGroups();
    }

    //GET GROUP BY ID
    @RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
    public ProjectGroup getGroupById(@PathVariable int id){
        return groupService.findGroupById(id);
    }

    // POST A NEW GROUP ADD STUDENTS AND SEND MAIL
    @RequestMapping(method=RequestMethod.POST, value="/groups")
    public void addGroup(@RequestBody ProjectGroup group){
        groupService.addGroup(group);
    }

    //UPDATE A GROUP
    @RequestMapping(method=RequestMethod.PUT, value="/groups/{id}")
    public void updateGroup(@RequestBody ProjectGroup group, @PathVariable int id){
        groupService.updateGroup(id, group);
    }

    //DELETE A GROUP
    @RequestMapping(method=RequestMethod.DELETE, value="/groups/{id}")
    public void deleteGroup(@PathVariable int id) {
        groupService.deleteGroup(id);
    }


}
