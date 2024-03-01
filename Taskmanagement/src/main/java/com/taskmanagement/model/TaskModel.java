package com.taskmanagement.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class TaskModel {
    
    @Id
    private String title;
    private String assignUserName;
    private String description;
    private LocalDate dueDate;
    private boolean completed;
    
    // Constructors
    public TaskModel() {
        // Default constructor required by JPA
    }
    
    public TaskModel(String title, String assignUserName, String description, LocalDate dueDate, boolean completed) {
        this.title = title;
        this.assignUserName = assignUserName;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
    }
    
    // Getters and Setters
    

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAssignUserName() {
        return assignUserName;
    }
    
    public void setAssignUserName(String assignUserName) {
        this.assignUserName = assignUserName;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
