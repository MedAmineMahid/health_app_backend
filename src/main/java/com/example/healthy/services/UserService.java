package com.example.healthy.services;

import com.example.healthy.security.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User saveUser(User user);
    User updateUser(User user);
    void deleteUserById(String id);
    void deleteAllUser();
    User getUserById(String id);
    List<User> getAllUsers();
}
