package com.example.healthy;

import com.example.healthy.entities.DailyMeal;
import com.example.healthy.entities.Ingredient;
import com.example.healthy.security.services.RoleService;
import com.example.healthy.services.DailyMealServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HealthyApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	RoleService roleService;
	@Test
	public void createUser(){

	}

	@Autowired
	DailyMealServiceImpl dailyMealService;
	@Test
	public void getMeals(){
		System.out.println("daily meals \n ");
		System.out.println(dailyMealService.getDailyMeals().size());

	}
	@Test
	public void addMealAndIngredients(){
		List<Ingredient> listIngredients = new ArrayList<>();
		Ingredient ingredient1 = new Ingredient("milk",1,"cup",12.4);
		Ingredient ingredient2 = new Ingredient("milk",1,"cup",12.4);
		Ingredient ingredient3 = new Ingredient("milk",1,"cup",12.4);
		listIngredients.add(ingredient1);
		listIngredients.add(ingredient2);
		listIngredients.add(ingredient3);
		DailyMeal d = new DailyMeal("Breakfast","newmeal",false,listIngredients);
		dailyMealService.addDailyMeal(d);
	}


}
