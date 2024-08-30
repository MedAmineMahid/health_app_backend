package com.example.healthy.controllers;

import com.example.healthy.security.entities.User;
import com.example.healthy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile/")
@CrossOrigin(origins = "http://localhost:4200")
public class profileController {
    @Autowired
    private UserService userService;

    @RequestMapping("update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
