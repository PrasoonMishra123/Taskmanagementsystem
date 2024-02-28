package com.taskmanagement.service;

import org.springframework.stereotype.Service;

import com.taskmanagement.model.CreateUser;

@Service
public interface UserService {
    String saveUser(CreateUser user);

}
