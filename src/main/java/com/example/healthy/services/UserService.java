package com.example.healthy.services;

import com.example.healthy.security.UserDTO;
import com.example.healthy.security.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    User saveUser(User user);
    User updateUser(User user);
    void deleteUserById(String id);
    void deleteAllUser();
    UserDTO getUserById(String id);
    List<User> getAllUsers();

    Optional<User> findUserById(String userId);
}
