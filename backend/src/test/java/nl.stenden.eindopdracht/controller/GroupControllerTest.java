package nl.stenden.eindopdracht.controller;

import nl.stenden.eindopdracht.Application;
import nl.stenden.eindopdracht.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from "classpath:/com/example/OrderServiceTest-context.xml"
@ContextConfiguration
public class GroupControllerTest {

    @Autowired
    private Application App;

    @Test
    public void testApplication() {
       assertThat(App).isNotNull();
    }
}