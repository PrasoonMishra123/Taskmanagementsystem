package com.taskmanagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class CreateUser {
    @Id
    private String username;
    private String password;
    
    // Constructor
    public CreateUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Default constructor
    public CreateUser() {
    }
    
    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
