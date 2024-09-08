package com.example.healthy.services;

import com.example.healthy.entities.Meal;
import com.example.healthy.repositories.MealRepository;
import com.example.healthy.security.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public Meal saveMeal(String mealName, List<String> ingredients, int totalCalories, User user) {
        Meal meal = new Meal();
        meal.setMealName(mealName);
        meal.setIngredients(ingredients);
        meal.setTotalCalories(totalCalories);
        meal.setUser(user);
        meal.setTime(LocalDateTime.now());

        return mealRepository.save(meal);
    }

    public List<Meal> getMealsByUserId(String userId) {
        return mealRepository.findByUser_UserId(userId);
    }

    public Meal getMealById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Meal not found with id: " + id));
    }

    public Meal updateMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public void deleteMealById(Long id) {
        mealRepository.deleteById(id);
    }
}



