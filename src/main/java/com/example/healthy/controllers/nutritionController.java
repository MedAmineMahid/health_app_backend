package com.example.healthy.controllers;
import com.example.healthy.entities.DailyMeal;
import com.example.healthy.entities.Ingredient;
import com.example.healthy.security.services.UserDetailServiceImpl;
import com.example.healthy.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    IngredientServiceImpl ingredientServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;
    //DailyMeal
    @PostMapping("/meals/save")
    public ResponseEntity<String> saveMeal(@RequestBody DailyMeal dailyMeal) {
        dailyMeal.setUser(userDetailService.getLoggedUser());
        DailyMeal d = dailyMealService.addDailyMeal(dailyMeal);
        List<Ingredient> ingredients = dailyMeal.getIngredients();
        System.out.println("Ingredients before persist: " + ingredients);
        for (Ingredient ingredient : ingredients) {
            ingredient.setDailyMeal(d);
            ingredientService.addIngredient(ingredient);
        }

        return ResponseEntity.ok("Meal saved successfully");
    }

    @GetMapping("/meals/list")
    public ResponseEntity<List<DailyMeal>> getMeals() {
        System.out.println( dailyMealService.getDailyMeals().size());

        return ResponseEntity.ok(dailyMealService.getDailyMeals());
    }

    @GetMapping("meals/listIngredients/{mealId}")
    public ResponseEntity<List<Ingredient>> getIngredients(@PathVariable Long mealId) {
        System.out.println("selected meal Id : "+mealId);
        return  ResponseEntity.ok(ingredientService.findAllByMealId(mealId));
    }

    @GetMapping("/calories/maxCalories")
    public Double getMaxCalories() {
        System.out.println("springboot max calories");
        //get logged user max daily calories
        return userServiceImpl.getMaxDailyCalories();
    }
}
