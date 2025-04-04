package com.example.stockmarket.controller;

import com.example.stockmarket.model.User;
import com.example.stockmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean created = userService.registerUser(user);
        return created ? "User registered successfully!" : "Registration failed.";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        boolean loggedIn = userService.login(user.getUsername(), user.getPassword());
        return loggedIn ? "Login successful!" : "Invalid username or password!";
    }
}
