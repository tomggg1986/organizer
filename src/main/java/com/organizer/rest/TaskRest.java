package com.organizer.rest;

import com.organizer.jpa.TaskRepository;
import com.organizer.model.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskRest {

    private TaskRepository taskRepository;

    public TaskRest(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public List<Task> getTaskByOwner(@RequestParam(value = "name") String name){
        return taskRepository.findByOwnerName(name);
    }

}
