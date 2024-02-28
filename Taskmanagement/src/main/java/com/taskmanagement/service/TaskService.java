package com.taskmanagement.service;

import com.taskmanagement.model.TaskModel;

public interface TaskService {

	String createTask(TaskModel task);

	String deleteTask(String userName);

}
