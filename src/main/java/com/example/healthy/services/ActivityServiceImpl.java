package com.example.healthy.services;

import com.example.healthy.entities.Activity;
import com.example.healthy.repositories.ActivityRepository;
import com.example.healthy.security.services.CalorieCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final CalorieCalculatorService calorieCalculatorService;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, CalorieCalculatorService calorieCalculatorService) {
        this.activityRepository = activityRepository;
        this.calorieCalculatorService = calorieCalculatorService;
    }

    @Override
    public Activity saveActivity(Activity activity) {
        double caloriesBurned = calorieCalculatorService.calculateCalories(activity.getTitle(), activity.getDuration());
        activity.setCaloriesBurned(caloriesBurned);
        return activityRepository.save(activity);
    }

    @Override
    public Activity updateActivity(Activity activity) {
        Optional<Activity> existingActivity = activityRepository.findById(activity.getId());
        if (existingActivity.isPresent()) {
            Activity updatedActivity = existingActivity.get();
            updatedActivity.setTitle(activity.getTitle());
            updatedActivity.setDescription(activity.getDescription());
            updatedActivity.setTime(activity.getTime());
            updatedActivity.setDuration(activity.getDuration());
            return activityRepository.save(updatedActivity);
        } else {
            throw new IllegalArgumentException("Activity not found with id: " + activity.getId());
        }
    }

    @Override
    public void deleteActivityById(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public void deleteAllActivities() {
        activityRepository.deleteAll();
    }

    @Override
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Activity not found with id: " + id));
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public List<Activity> getActivitiesByUserId(String userId) {
        return activityRepository.findByUser_UserId(userId);
    }
}
