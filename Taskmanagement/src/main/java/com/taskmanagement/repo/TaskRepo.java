package com.taskmanagement.repo;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;

import com.taskmanagement.model.TaskModel;


// TaskRepository.java

public interface TaskRepo extends JpaRepository<TaskModel, String> {

	Optional<TaskModel> findByAssignUserName(String taskId);
	 List<TaskModel> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAssignUserNameContainingIgnoreCase(String title, String description, String assignUserName);

    
    List<TaskModel> findByCompleted(boolean completed);
    
    List<TaskModel> findByDueDate(LocalDate dueDate);
	List<TaskModel> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAssignUserNameContainingIgnoreCaseAndCompleted(
			String searchTerm, String searchTerm2, String searchTerm3, LocalDate dueDate);
	List<TaskModel> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAssignUserNameContainingIgnoreCaseAndCompletedAndDueDate(
			String searchTerm, String searchTerm2, String searchTerm3, Boolean completed, LocalDate dueDate);
	List<TaskModel> findByCompletedAndDueDate(Boolean completed, LocalDate dueDate);
	List<TaskModel> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAssignUserNameContainingIgnoreCaseAndCompleted(
			String searchTerm, String searchTerm2, String searchTerm3, Boolean completed);
	List<TaskModel> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAssignUserNameContainingIgnoreCaseAndDueDate(
			String searchTerm, String searchTerm2, String searchTerm3, LocalDate dueDate);

	
}