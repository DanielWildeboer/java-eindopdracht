package nl.stenden.eindopdracht.controller;

import com.google.gson.Gson;
import nl.stenden.eindopdracht.model.Student;
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
public class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void students() throws Exception{
        List<Student> students = Arrays.asList(
                new Student("test@test.com", "Hubert-Jason", "Cumberdale", 1234),
                new Student("test@test.com", "Jeremy", "Fisher", 9000));
        Mockito.when(studentService.findAll()).thenReturn(students);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].email", is("test@test.com")))
                .andExpect(jsonPath("$[0].firstName", is("Hubert-Jason")))
                .andExpect(jsonPath("$[0].lastName", is("Cumberdale")))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].email", is("test@test.com")))
                .andExpect(jsonPath("$[0].firstName", is("Jeremy")))
                .andExpect(jsonPath("$[0].lastName", is("Fisher")));
        verify(studentService, times(1)).findAll();
        verifyNoMoreInteractions(studentService);
    }


    @Test
    public void testGetById() throws Exception {
        Student student =new Student("test@test.com", "Student", "McStudentface", 5555);
        Mockito.when(studentService.findById(5555)).thenReturn(student);
        mockMvc.perform(get("api/students/{id}", 5555))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("Daenerys Targaryen")));
        verify(studentService, times(1)).findById(1);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void addStudent()throws Exception {
        Student student = new Student();
        student.setFirstName("test-naam");
        student.setLastName("test-achternaam");
        student.setEmail("test@email.com");
        student.setStudentNumber(123456);

        String json = new Gson().toJson(student);
        mockMvc.perform(
                post("api/student").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                        .andExpect(status().isCreated());
    }

    @Test
    public void test_update_user_success() throws Exception {
        Student student = new Student("test@test.com", "Student", "McStudentface", 4444);
        Mockito.when(studentService.findById(student.getId())).thenReturn(student);
        mockMvc.perform(
                put("api/students/{id}", student.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(studentService, times(1)).findById(student.getId());
        verify(studentService, times(1)).updateStudent(student.getId(), student);
        verifyNoMoreInteractions(studentService);
    }
    @Test
    public void test_delete_student() throws Exception {
        Student student = new Student("test@test.com", "Student", "McStudentface", 4444);
        when(studentService.findById(student.getId())).thenReturn(student);
        doNothing().when(studentService).delete(student.getId());
        mockMvc.perform(
                delete("api/students/{id}", student.getId()))
                .andExpect(status().isOk());
        verify(studentService, times(1)).findById(student.getId());
        verify(studentService, times(1)).delete(student.getId());
        verifyNoMoreInteractions(studentService);
    }
}