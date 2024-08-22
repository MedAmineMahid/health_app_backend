package com.example.healthy.services;

import com.example.healthy.entities.DailyMeal;
import com.example.healthy.repositories.DailyMealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DailyMealServiceImpl implements DailyMealService {
    @Autowired
    private DailyMealRepository dailyMealRepository;

    @Override
    public DailyMeal addDailyMeal(DailyMeal dailyMeal) {
        return dailyMealRepository.save(dailyMeal);
    }

    @Override
    public List<DailyMeal> getDailyMeals() {
        return dailyMealRepository.findAll();
    }

    @Override
    public DailyMeal getDailyMealById(Long id) {
        return dailyMealRepository.findById(id).get();
    }

    @Override
    public DailyMeal updateDailyMeal(DailyMeal dailyMeal) {
        return dailyMealRepository.save(dailyMeal);
    }

    @Override
    public void deleteDailyMeal(Long id) {
dailyMealRepository.deleteById(id);
    }
}
