package com.bird.controller;

import com.bird.dto.BirdDTO;
import com.bird.dto.TaskDTO;
import com.bird.model.Bird;
import com.bird.model.Task;
import com.bird.service.BirdService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import java.util.List;

@RestController
@RequestMapping(value = "/api/bird", produces = MediaType.APPLICATION_JSON_VALUE)
public class BirdController {

    final protected org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BirdService birdService;

    @GetMapping("/list")
    public List<Bird> list() {
        return birdService.list();
    }

    @PostMapping()
    public ResponseEntity<?> create(
            @RequestBody BirdDTO birdDTO) {
        birdService.createOrUpdate(birdDTO);
        String response = Json.createObjectBuilder().add("result", "ok").build().toString();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable("id") Long id) {
        Bird bird = birdService.findById(id);
        LOGGER.info("Read Bird: " + bird);
        return new ResponseEntity<>(bird, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> update(
            @RequestBody BirdDTO birdDTO) {
        LOGGER.info("Update Task: " + birdDTO);
        Bird bird = birdService.createOrUpdate(birdDTO);
        return new ResponseEntity<>(bird, HttpStatus.OK);
    }


}
