package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.ProjectGroup;
import nl.stenden.eindopdracht.service.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class GroupController {

    @Autowired
    private GroupServiceImpl groupService;

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public Set<ProjectGroup> getGroups(){
        return groupService.findAllGroups();
    }

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
    public ProjectGroup getGroupById(@PathVariable String id){
        return groupService.findGroupById(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/groups")
    public void addGroup(@RequestBody ProjectGroup group){
        groupService.addGroup(group);
    }

}
