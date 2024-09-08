package com.example.healthy.security.services;

import org.springframework.stereotype.Service;

@Service
public class CalorieCalculatorService {

    public double calculateCalories(String activityType, int duration) {
        double caloriesBurned = 0;

        switch (activityType.toLowerCase()) {
            case "running":
                caloriesBurned = (100.0 / 60) * duration;
                break;
            case "walking":
                caloriesBurned = (80.0 / 60) * duration;
                break;
            case "cycling":
                caloriesBurned = (50.0 / 60) * duration;
                break;
            case "yoga":
                caloriesBurned = (250.0 / 60) * duration;
                break;
            case "hiit":
                caloriesBurned = (500.0 / 60) * duration;
                break;
            default:
                throw new IllegalArgumentException("Unsupported activity type: " + activityType);
        }

        return caloriesBurned;
    }
}
