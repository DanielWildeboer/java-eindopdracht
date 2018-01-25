package nl.stenden.eindopdracht.controller;

import com.google.gson.Gson;
import nl.stenden.eindopdracht.model.ProjectGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GroupController.class})
@WebAppConfiguration
public class GroupControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getGroups() throws Exception {
        ProjectGroup projectGroup = new ProjectGroup();
        projectGroup.setUserId("1234");
        projectGroup.setSubject("Testclasses maken");
        projectGroup.setGrade(4.2f);
        projectGroup.setStatus(true);
        projectGroup.setName("GroupyMcGroupface");
        projectGroup.setId(1234);

        String json = new Gson().toJson(projectGroup);
        mockMvc.perform(
                post("api/group")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void getGroupById() {
    }

    @Test
    public void getGroupsByUserId() {
    }

    @Test
    public void addGroup() throws Exception {
        ProjectGroup projectGroup = new ProjectGroup();
        projectGroup.setUserId("1234");
        projectGroup.setSubject("Testclasses maken");
        projectGroup.setGrade(4.2f);
        projectGroup.setStatus(true);
        projectGroup.setName("GroupyMcGroupface");
        projectGroup.setId(1234);

        String json = new Gson().toJson(projectGroup);
        mockMvc.perform(
                post("api/group")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void addStudent() {

    }

    @Test
    public void updateGroup() {
//        int id = 123;
//        ProjectGroup projectGroup = new ProjectGroup();
//        projectGroup.setUserId("1234");
//        projectGroup.setSubject("Testclasses maken");
//        projectGroup.setGrade(4.2f);
//        projectGroup.setStatus(true);
//        projectGroup.setName("GroupyMcGroupface");
//        projectGroup.setId(1234);
//
//        String json = new Gson().toJson(projectGroup);
//        mockMvc.perform(
//                post("api/group/" + id)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isCreated());
    }

    @Test
    public void deleteGroup() {
    }
}