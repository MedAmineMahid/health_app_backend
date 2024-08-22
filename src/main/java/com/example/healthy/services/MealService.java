package com.example.healthy.services;

import org.springframework.stereotype.Service;

@Service
public interface MealService {
    public String calculateMealCalories(String meal);

    String getIngredien(String name);
}
