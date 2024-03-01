package com.taskmanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.taskmanagement.model.TaskModel;

public interface TaskService {

	String createTask(TaskModel task);

	String deleteTask(String taskName);

	TaskModel updateTask(String taskId, TaskModel updatedTask);

	TaskModel markTaskAsComplete(String taskId);

	TaskModel markTaskAsIncomplete(String taskId);

	List<TaskModel> searchAndFilterTasks(String searchTerm, Boolean completed, LocalDate dueDate);

}
