package com.taskmanagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    
      //deleting the task
    @PostMapping("/deletetask/{taskName}")
    @ResponseBody
    public ResponseEntity<String> deleteTask(@PathVariable("taskName") String taskName) {
        String result = taskService.deleteTask(taskName);
        if (result.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        return ResponseEntity.ok(result);
    }
 
    
    @PostMapping("/update/{taskId}")
    public ResponseEntity<TaskModel> updateTask(
            @PathVariable("taskId") String taskId,
            @RequestBody TaskModel updatedTask) {
        TaskModel task = taskService.updateTask(taskId, updatedTask);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Mark a task as complete
    @PostMapping("/complete/{taskId}")
    public ResponseEntity<TaskModel> markTaskAsComplete(@PathVariable("taskId") String taskId) {
        TaskModel task = taskService.markTaskAsComplete(taskId);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Mark a task as incomplete
    @PostMapping("/incomplete/{taskId}")
    public ResponseEntity<TaskModel> markTaskAsIncomplete(@PathVariable("taskId") String taskId) {
        TaskModel task = taskService.markTaskAsIncomplete(taskId);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
       
    //for search data    
    @GetMapping("/searchAndFilter")
    public List<TaskModel> searchAndFilterTasks(
            @RequestParam(value = "searchTerm", required = false) String searchTerm,
            @RequestParam(value = "completed", required = false) Boolean completed,
            @RequestParam(value = "dueDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
        return taskService.searchAndFilterTasks(searchTerm, completed, dueDate);
    }
}
