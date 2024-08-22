package com.example.healthy.services;

import com.example.healthy.entities.Activity;
import com.example.healthy.entities.HealthData;
import com.example.healthy.repositories.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService{
    private ActivityRepository activityRepository;
    @Override
    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity updateActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public void deleteActivityById(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public void deleteAllActivity() {
        activityRepository.deleteAll();
    }

    @Override
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).get();    }

    @Override
    public List<Activity> getAllActivity() {
        return activityRepository.findAll();    }
}
