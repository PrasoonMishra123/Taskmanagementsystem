package com.taskmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanagement.model.TaskModel;


// TaskRepository.java

public interface TaskRepo extends JpaRepository<TaskModel, String> {
}