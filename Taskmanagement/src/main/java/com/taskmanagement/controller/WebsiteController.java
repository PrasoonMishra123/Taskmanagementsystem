package com.taskmanagement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.taskmanagement.model.CreateUser;
import com.taskmanagement.service.LoginService;
import com.taskmanagement.service.UserService;

@RestController
@RequestMapping("/")
public class WebsiteController {
    
    private final UserService userService;
    private final LoginService loginService;
    
    @Autowired
    public WebsiteController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }
    
    @RequestMapping("/")
    public ModelAndView homePage() {
        ModelAndView model = new ModelAndView("register");
        return model;
    }
    
    // Create user account
    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody CreateUser user) {
        String response = userService.saveUser(user);
        return ResponseEntity.ok().body(response);
    }
   
    // Login controller method
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (loginService.authenticate(username, password)) {
            return ResponseEntity.ok().body("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
