package nl.stenden.eindopdracht.tests;

import nl.stenden.eindopdracht.controller.GroupController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Policy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(GroupController.class)
public class GroupControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void allGroupsTest(){
        final int groupNumber = 5;
        mvc.perform(get(GroupController.URL, groupNumber).accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andExpect(content().string("testing"));
    }
}
