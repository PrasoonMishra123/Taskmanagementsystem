package com.taskmanagement.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import com.taskmanagement.model.TaskModel;
import com.taskmanagement.repo.TaskRepo;

@Service
public class TaskServiceImp implements TaskService {
    @Autowired
    private TaskRepo taskRepository;

   @Override
    public String createTask(TaskModel task) {
         taskRepository.save(task);
         return "Task created for user";
    }


   @Override
   public String deleteTask(String taskName) {
       try {
           taskRepository.deleteById(taskName);
           return "Task deleted successfully";
       } catch (Exception e) {
           return "Error deleting task: " + e.getMessage();
       }
   }  
    @Override
    public TaskModel updateTask(String taskId, TaskModel updatedTask) {
        Optional<TaskModel> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
        	TaskModel task = new TaskModel();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setDueDate(updatedTask.getDueDate());
            task.setAssignUserName(updatedTask.getAssignUserName());
            return taskRepository.save(task);
        } else {
            return null; // Task not found
        }
    }

    // Mark a task as complete
    
    @Override
    public TaskModel markTaskAsComplete(String taskId) {
        Optional<TaskModel> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            TaskModel task = optionalTask.get();
            task.setCompleted(true);
            return taskRepository.save(task);
        } else {
            return null; // Task not found
        }
    }

    @Override
    public TaskModel markTaskAsIncomplete(String taskId) {
        Optional<TaskModel> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            TaskModel task = optionalTask.get();
            task.setCompleted(false);
            return taskRepository.save(task);
        } else {
            return null; // Task not found
        }
    }
    
    //for search operation
    
    @Override
    public List<TaskModel> searchAndFilterTasks(String searchTerm, Boolean completed, LocalDate dueDate) {
        if (searchTerm != null && !searchTerm.isEmpty()) {
            // Search by title, description, or assign user name
            if (completed != null && dueDate != null) {
                // Search by searchTerm, filter by completion status and due date
                return taskRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAssignUserNameContainingIgnoreCaseAndCompletedAndDueDate(
                    searchTerm, searchTerm, searchTerm, completed, dueDate);
            } else if (completed != null) {
                // Search by searchTerm and filter by completion status
                return taskRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAssignUserNameContainingIgnoreCaseAndCompleted(
                    searchTerm, searchTerm, searchTerm, completed);
            } else if (dueDate != null) {
                // Search by searchTerm and filter by due date
                return taskRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAssignUserNameContainingIgnoreCaseAndDueDate(
                    searchTerm, searchTerm, searchTerm, dueDate);
            } else {
                // Only search by searchTerm
                return taskRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrAssignUserNameContainingIgnoreCase(
                    searchTerm, searchTerm, searchTerm);
            }
        } else {
            // No searchTerm provided, only apply filters
            if (completed != null && dueDate != null) {
                // Filter by completion status and due date
                return taskRepository.findByCompletedAndDueDate(completed, dueDate);
            } else if (completed != null) {
                // Filter by completion status
                return taskRepository.findByCompleted(completed);
            } else if (dueDate != null) {
                // Filter by due date
                return taskRepository.findByDueDate(dueDate);
            } else {
                // No search term or filters provided, return all tasks
                return taskRepository.findAll();
            }
        }
    }
    
    
    
       
    
    
}