package com.example.healthy.services;

import com.example.healthy.security.entities.User;
import com.example.healthy.security.repositories.UserRepository;
import com.example.healthy.security.services.UserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteAllUser() {
        userRepository.deleteAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Autowired
    UserDetailServiceImpl userDetailService;
    @Override
    public Double getMaxDailyCalories() {
        User user = userDetailService.getLoggedUser();
        return user.getMaxDailyCalories();
    }
}
