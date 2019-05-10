package com.bird.controller;

import com.bird.dto.TaskDTO;
import com.bird.model.Task;
import com.bird.service.TaskService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import java.util.List;

@RestController
@RequestMapping(value = "/api/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    final protected org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskService taskService;

    @GetMapping("/list")
    public List<Task> list() {
        return taskService.list();
    }

    @PostMapping()
    public ResponseEntity<?> create(
            @RequestBody TaskDTO taskDTO) {
        taskService.createOrUpdate(taskDTO);
        String response = Json.createObjectBuilder().add("result", "ok").build().toString();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable("id") Long id) {
        Task task = taskService.findById(id);
        LOGGER.info("Read Task: " + task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> update(
            @RequestBody TaskDTO taskDTO) {
        LOGGER.info("Update Task: " + taskDTO);
        Task task = taskService.createOrUpdate(taskDTO);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }


}
