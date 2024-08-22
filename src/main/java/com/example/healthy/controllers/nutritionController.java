package com.example.healthy.controllers;

import com.example.healthy.entities.DailyMeal;
import com.example.healthy.entities.Ingredient;
import com.example.healthy.services.DailyMealService;
import com.example.healthy.services.IngredientService;
import com.example.healthy.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class nutritionController {
    @Autowired
    MealService mealService;

    @RequestMapping("/caloriesCaluculator/{foodQuery}")
    public String caloriesCaluculator(@PathVariable String foodQuery ) {
        System.out.println("food query : "+foodQuery);
       return mealService.calculateMealCalories(foodQuery);
    }

    @RequestMapping("/searchIngredient/{foodName}")
    public String searchIngredient(@PathVariable String foodName ) {
        System.err.println("searchIngredient");
        return mealService.getIngredien(foodName);
    }

    @Autowired
    DailyMealService dailyMealService;
    @Autowired
    IngredientService ingredientService;
    //DailyMeal
    @PostMapping("/meals/save")
    public ResponseEntity<String> saveMeal(@RequestBody DailyMeal dailyMeal) {
        dailyMealService.addDailyMeal(dailyMeal);
        System.out.println("\n saved meal : \n "+dailyMeal.toString());
        return ResponseEntity.ok("Meal saved successfully");
    }}
