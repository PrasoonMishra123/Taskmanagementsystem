package com.taskmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.model.CreateUser;
import com.taskmanagement.repo.UserRepo;

@Service
public class UserServiceImp implements UserService {
    
    private final UserRepo repo;

    @Autowired
    public UserServiceImp(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public String saveUser(CreateUser user) {
        // Check if user already exists
        if (repo.findByUsername(user.getUsername()) != null) {
            return "User already exists";
        }

        // Save the new user
        repo.save(user);
        return "New user created";
    }
}
