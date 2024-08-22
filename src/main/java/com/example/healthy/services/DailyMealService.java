package com.example.healthy.services;

import com.example.healthy.entities.DailyMeal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DailyMealService {
    public DailyMeal addDailyMeal(DailyMeal dailyMeal);
    public List<DailyMeal> getDailyMeals();

    DailyMeal getDailyMealById(Long id);

    public DailyMeal updateDailyMeal(DailyMeal dailyMeal);

    void deleteDailyMeal(Long id);
}
