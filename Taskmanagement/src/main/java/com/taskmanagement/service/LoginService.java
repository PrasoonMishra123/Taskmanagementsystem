package com.taskmanagement.service;

import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    public boolean authenticate(String username, String password);

}
