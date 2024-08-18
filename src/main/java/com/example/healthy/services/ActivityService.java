package com.example.healthy.services;

import com.example.healthy.entities.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {
    Activity saveActivity(Activity activity);
    Activity updateActivity(Activity activity);
    void deleteActivityById(Long id);
    void deleteAllActivities();
    Activity getActivityById(Long id);
    List<Activity> getAllActivities();
    List<Activity> getActivitiesByMedicalRecordId(Long medicalRecordId);

    List<Activity> getActivitiesByUserId(String userId);
}
