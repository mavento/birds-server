package com.bird;

import com.bird.model.Role;
import com.bird.model.Task;
import com.bird.model.User;
import com.bird.service.TaskService;
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
	TaskService taskService;

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

		Task task = new Task();
		task.setDescription("Log Work");
		taskService.save(task);


	}

}

