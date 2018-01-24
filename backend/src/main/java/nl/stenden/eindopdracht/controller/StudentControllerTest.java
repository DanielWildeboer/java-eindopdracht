package nl.stenden.eindopdracht.controller;

import com.google.gson.Gson;
import nl.stenden.eindopdracht.model.Student;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StudentController.class})
@WebAppConfiguration
public class StudentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void students() {
    }

    @Test
    public void student() {
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
                post("api/student")
                   .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateStudent() {
    }

    @Test
    public void removeStudent() {
    }
}