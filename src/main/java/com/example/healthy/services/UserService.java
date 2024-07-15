package com.example.healthy.services;

import com.example.healthy.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User saveUser(User user);
    User updateUser(User user);
    void deleteUserById(Long id);
    void deleteAllUser();
    User getUserById(Long id);
    List<User> getAllUsers();
}
