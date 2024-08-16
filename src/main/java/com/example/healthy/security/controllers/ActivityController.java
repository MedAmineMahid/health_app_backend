package com.example.healthy.security.controllers;

import com.example.healthy.entities.Activity;
import com.example.healthy.security.entities.User;
import com.example.healthy.security.repositories.UserRepository;
import com.example.healthy.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;
    private final UserRepository userRepository;

    @Autowired
    public ActivityController(ActivityService activityService, UserRepository userRepository) {
        this.activityService = activityService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public Activity createActivity(@RequestBody Activity activity) {
        String userId = getSignedInUserId();
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            activity.setUser(user.get());
            return activityService.saveActivity(activity);
        } else {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
    }

    @PutMapping("/{id}")
    public Activity updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        activity.setId(id);
        return activityService.updateActivity(activity);
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable Long id) {
        activityService.deleteActivityById(id);
    }

    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable Long id) {
        return activityService.getActivityById(id);
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @GetMapping("/medicalRecord/{medicalRecordId}")
    public List<Activity> getActivitiesByMedicalRecordId(@PathVariable Long medicalRecordId) {
        return activityService.getActivitiesByMedicalRecordId(medicalRecordId);
    }
@GetMapping ("/user")
    private String getSignedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(currentPrincipalName));
        if (user.isPresent()) {
            return user.get().getUserId();
        } else {
            throw new IllegalArgumentException("User not found: " + currentPrincipalName);
        }
    }
}
