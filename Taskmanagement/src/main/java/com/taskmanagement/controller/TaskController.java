package com.taskmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.model.TaskModel;
import com.taskmanagement.service.TaskServiceImp;

// TaskController.java
@RestController
public class TaskController {
    @Autowired
    private TaskServiceImp taskService;

    @PostMapping("/createtask")
    public ResponseEntity<String> createTask(@RequestBody TaskModel task) {
         String createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }
}
