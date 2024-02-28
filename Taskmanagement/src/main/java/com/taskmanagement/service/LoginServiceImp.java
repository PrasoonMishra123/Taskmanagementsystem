package com.taskmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.model.CreateUser;
import com.taskmanagement.repo.UserRepo;
@Service
public class LoginServiceImp  implements LoginService {
	@Autowired
    private UserRepo userRepo;
   
	@Override
    public boolean authenticate(String username, String password) {
        CreateUser user = userRepo.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }


}
