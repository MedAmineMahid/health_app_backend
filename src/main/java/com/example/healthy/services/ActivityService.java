package com.example.healthy.services;

import com.example.healthy.entities.Activity;
import com.example.healthy.entities.HealthData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {
    Activity saveActivity(Activity activity);
    Activity updateActivity(Activity activity);
    void deleteActivityById(Long id);
    void deleteAllActivity();
    Activity getActivityById(Long id);
    List<Activity> getAllActivity();
}
