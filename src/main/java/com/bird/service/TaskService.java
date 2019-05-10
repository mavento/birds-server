package com.bird.service;

import com.bird.dto.TaskDTO;
import com.bird.model.Task;
import com.bird.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task createOrUpdate(TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskDTO.getId()).orElse(new Task());
        BeanUtils.copyProperties(taskDTO, task);
        this.save(task);
        return null;
    }

    public List<Task> list() {
        List<Task> list = taskRepository.findAll();
        return list;
    }

    public void save(Task task) {
        taskRepository.save(task);
    }


}
