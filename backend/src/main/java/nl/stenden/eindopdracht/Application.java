package nl.stenden.eindopdracht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.FileSystemXmlApplicationContext;


@EnableAutoConfiguration
@ComponentScan("nl.stenden")
public class Application {
    /**
     * main method to keep the application alive
     * @param args
     */
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args

        );
    }
}

