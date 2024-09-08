package com.example.healthy.services;

import com.example.healthy.security.ActivityDTO;
import com.example.healthy.security.UserDTO;
import com.example.healthy.security.entities.User;
import com.example.healthy.security.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> findUserById(String userId) {
        return userRepository.findById(userId);
    }

    public UserDTO getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setAge(user.getAge());
        userDTO.setGender(user.getGender());

        userDTO.setGoals(user.getGoal());

        // Map activities
        List<ActivityDTO> activities = user.getActivities().stream().map(activity -> {
            ActivityDTO dto = new ActivityDTO();
            dto.setId(activity.getId());
            dto.setTitle(activity.getTitle());
            dto.setDescription(activity.getDescription());
            dto.setDuration(activity.getDuration());
            dto.setTime(activity.getTime());
            dto.setUserId(user.getUserId());
            dto.setUsername(user.getUsername());
            return dto;
        }).collect(Collectors.toList());

        userDTO.setActivities(activities);
        return userDTO;
    }
}

