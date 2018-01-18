package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.model.ProjectGroup;
import nl.stenden.eindopdracht.model.User;
import nl.stenden.eindopdracht.model.Student;
import nl.stenden.eindopdracht.repository.GroupRepository;
import nl.stenden.eindopdracht.repository.StudentRepository;
import nl.stenden.eindopdracht.service.GroupServiceImpl;
import nl.stenden.eindopdracht.service.TokenServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class GroupController {

    @Autowired
    private GroupServiceImpl groupService;

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
    public ResponseEntity getGroupById(@PathVariable int id){
        ProjectGroup group = groupService.findGroupById(id);
        return new ResponseEntity<>(group, HttpStatus.FOUND);
    }

    // POST A NEW GROUP AND RETURN THE ID OF THE GROUP WHICH CAN BE USED TO ADD USERS TO THE GROUP
    @RequestMapping(method=RequestMethod.POST, value="api/group")
    public ResponseEntity addGroup(@ModelAttribute ProjectGroup group){
        try {
            groupService.addGroup(group);
            group.setStatus(false);
        } catch (Exception e)
        {
            logger.info(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
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
    public ResponseEntity updateGroup(@ModelAttribute ProjectGroup group, @PathVariable int id){
        try {
            groupService.updateGroup(id, group);
        } catch(Exception e){
            logger.info(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DELETE A GROUP
    @RequestMapping(method=RequestMethod.DELETE, value="api/group/{id}")
    public ResponseEntity deleteGroup(@PathVariable int id) {
        try{
            groupService.deleteGroup(id);
        } catch(Exception e){
            logger.info(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
