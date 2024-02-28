package com.taskmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.model.TaskModel;
import com.taskmanagement.repo.TaskRepo;

@Service
public class TaskServiceImp implements TaskService {
    @Autowired
    private TaskRepo taskRepository;

   
    public String createTask(TaskModel task) {
         taskRepository.save(task);
         return "Task created for user";
    }



    public String deleteTask(String userName) {
        taskRepository.deleteById(userName);
        return "Task deleted Successfully";
    }
}