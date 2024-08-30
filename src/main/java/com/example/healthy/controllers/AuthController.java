package com.example.healthy.controllers;

import com.example.healthy.security.entities.User;
import com.example.healthy.security.services.AccountService;
import com.example.healthy.security.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    @Autowired
    private AccountService accountService;
    @RequestMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        accountService.createUser(user);
        return ResponseEntity.ok("Meal saved successfully");
    }

    @Autowired
    UserDetailServiceImpl userDetailService;
    @GetMapping("/loggedUser")
    public User getLoggedUser() {
        return userDetailService.getLoggedUser();
    }
}
