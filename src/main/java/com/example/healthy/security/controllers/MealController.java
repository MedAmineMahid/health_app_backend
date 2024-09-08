package com.example.healthy.security.controllers;

import com.example.healthy.entities.Meal;
import com.example.healthy.security.entities.User;
import com.example.healthy.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.healthy.security.repositories.UserRepository;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    private final MealService mealService;
    private final UserRepository userRepository;

    @Autowired
    public MealController(MealService mealService, UserRepository userRepository) {
        this.mealService = mealService;
        this.userRepository = userRepository;
    }

    @PostMapping

    public ResponseEntity<?> createMeal(@RequestBody Map<String, Object> mealData) {
        try {
            String userId = getSignedInUserId();
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                String mealName = (String) mealData.get("mealName");
                List<String> ingredients = (List<String>) mealData.get("ingredients");
                int totalCalories = (Integer) mealData.get("totalCalories");

                Meal meal = mealService.saveMeal(mealName, ingredients, totalCalories, user.get());
                return ResponseEntity.status(HttpStatus.CREATED).body(meal);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found with id: " + userId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating meal: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}/meals")
    public ResponseEntity<List<Meal>> getUserMeals(@PathVariable String userId) {
        try {
            List<Meal> meals = mealService.getMealsByUserId(userId);
            if (meals.isEmpty()) {
                return ResponseEntity.noContent().build(); // No content if the list is empty
            }
            return ResponseEntity.ok(meals);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable Long id) {
        try {
            Meal meal = mealService.getMealById(id);
            return ResponseEntity.ok(meal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMeal(@PathVariable Long id, @RequestBody Meal meal) {
        try {
            meal.setId(id);
            Meal updatedMeal = mealService.updateMeal(meal);
            return ResponseEntity.ok(updatedMeal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating meal: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeal(@PathVariable Long id) {
        try {
            mealService.deleteMealById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting meal: " + e.getMessage());
        }
    }

    private String getSignedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(currentPrincipalName));
        if (user.isPresent()) {
            return user.get().getUserId();
        } else {
            throw new IllegalArgumentException("User not found: " + currentPrincipalName);
        }
    }
}
