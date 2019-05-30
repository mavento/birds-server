package com.bird;

import com.bird.model.Bird;
import com.bird.model.Role;
import com.bird.model.User;
import com.bird.service.BirdService;
import com.bird.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    UserService userService;
    @Autowired
    BirdService birdService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... params) throws Exception {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setFirstName("Jon");
        admin.setLastName("Doe");
        admin.setEmail("admin@email.com");
        admin.setRoles(new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));

        userService.signup(admin);

        User client = new User();
        client.setUsername("client");
        client.setPassword("client");
        admin.setFirstName("Max");
        admin.setLastName("Mustermann");
        client.setEmail("client@email.com");
        client.setRoles(new ArrayList<>(Arrays.asList(Role.ROLE_CLIENT)));

        userService.signup(client);

        for (int i = 1; i <+ 11; i++) {
            Bird bird = new Bird();
            bird.setName("Bird #" + i);
            bird.setDescription("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Beatae, illum.");
            bird.setImagePath("assets/sample-data/bird" + i + ".jpg");
            birdService.save(bird);

        }


    }

}

