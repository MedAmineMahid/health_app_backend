package com.example.healthy.security.services;

import org.springframework.stereotype.Service;

@Service
public class CalorieCalculatorService {

    public int calculateCalories(String activityType, int duration) {
        int caloriesBurned = 0;

        switch (activityType.toLowerCase()) {
            case "running":
                caloriesBurned = (100/ 60) * duration;
                break;
            case "walking":
                caloriesBurned = (80/ 60) * duration;
                break;
            case "cycling":
                caloriesBurned = (50/ 60) * duration;
                break;
            case "yoga":
                caloriesBurned = (250/ 60) * duration;
                break;
            case "hiit":
                caloriesBurned = (500 / 60) * duration;
                break;
            default:
                throw new IllegalArgumentException("Unsupported activity type: " + activityType);
        }

        return caloriesBurned;
    }
}
