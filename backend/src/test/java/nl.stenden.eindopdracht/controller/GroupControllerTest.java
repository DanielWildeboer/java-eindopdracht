package nl.stenden.eindopdracht.controller;

import com.google.gson.Gson;
import nl.stenden.eindopdracht.model.ProjectGroup;
import nl.stenden.eindopdracht.model.Student;
import nl.stenden.eindopdracht.service.GroupService;
import nl.stenden.eindopdracht.service.StudentService;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StudentController.class})
@WebAppConfiguration
public class GroupControllerTest {

    private MockMvc mockMvc;
    @Mock
    private GroupService groupService;

    @InjectMocks
    private GroupController groupController;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(groupController).build();
    }

    @Test
    public void groups() throws Exception{
        List<ProjectGroup> groups = Arrays.asList(
                new ProjectGroup(1, "1", "JavaPlebs", "Javaminor", 75),
                new ProjectGroup(2, "2", "PindaPower", "Javaminor", 69));
        Mockito.when(groupService.findAllGroups()).thenReturn(groupService.findAllGroups());
        mockMvc.perform(get("api/groups"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("JavaPlebs")))
                .andExpect(jsonPath("$[0].subject", is("Javaminor")))
                .andExpect(jsonPath("$[0].grade", is(75)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].name", is("PindaPower")))
                .andExpect(jsonPath("$[0].subject", is("Javaminor")))
                .andExpect(jsonPath("$[0].grade", is(69)));
        verify(groupService, times(1)).findAllGroups();
        verifyNoMoreInteractions(groupService);
    }

    @Test
    public void testGetById() throws Exception {
        ProjectGroup group = new ProjectGroup(3, "3", "GroupyMcGroupFace", "Javaminor", 75);
        Mockito.when(groupService.findGroupById(5555)).thenReturn(group);
        mockMvc.perform(get("api/groups/{id}", 3))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("GroupyMcGroupFace")))
                .andExpect(jsonPath("$.subeject", is("Javaminor")))
                .andExpect(jsonPath("$.grade", is("75")));
        verify(groupService, times(1)).findGroupById(3);
        verifyNoMoreInteractions(groupService);
    }

    @Test
    public void testAddGroup()throws Exception {
        ProjectGroup group = new ProjectGroup();
        group.setName("GradeMaster");
        group.setSubject("JavaMinor");
        group.setGrade(99);
        group.setId(5);
        String json = new Gson().toJson(group);
        mockMvc.perform(
                post("api/groups").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateGroup() throws Exception {
        ProjectGroup group = new ProjectGroup(6, "6", "Group6", "Javaminor", 77);
        Mockito.when(groupService.findGroupById(group.getId())).thenReturn(group);
        mockMvc.perform(
                put("api/groups/{id}", group.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(groupService, times(1)).findGroupById(group.getId());
        verify(groupService, times(1)).updateGroup(group.getId(), group);
        verifyNoMoreInteractions(groupService);
    }
    @Test
    public void testDeleteGroup() throws Exception {
        ProjectGroup group = new ProjectGroup(7, "7", "DeletableGroup", "Javaminor", 11);
        when(groupService.findGroupById(group.getId())).thenReturn(group);
        doNothing().when(groupService).deleteGroup(group.getId());
        mockMvc.perform(
                delete("api/groups/{id}", group.getId()))
                .andExpect(status().isOk());
        verify(groupService, times(1)).findGroupById(group.getId());
        verify(groupService, times(1)).deleteGroup(group.getId());
        verifyNoMoreInteractions(groupService);
    }
}