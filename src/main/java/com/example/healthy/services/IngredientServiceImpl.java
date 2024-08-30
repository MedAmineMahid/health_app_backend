package com.example.healthy.services;

import com.example.healthy.entities.Ingredient;
import com.example.healthy.repositories.IngredientReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientReposiory ingredientReposiory;
    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientReposiory.save(ingredient);
    }

    @Override
    public List<Ingredient> findAll() {
        return ingredientReposiory.findAll();
    }

    @Override
    public Ingredient findById(Long id) {
        return ingredientReposiory.findById(id).get();
    }

    @Override
    public Ingredient update(Ingredient ingredient) {
        return ingredientReposiory.save(ingredient);
    }

    @Override
    public void deleteById(Long id) {
        ingredientReposiory.deleteById(id);
    }

    @Override
    public List<Ingredient> findAllByMealId(Long mealId) {
        return ingredientReposiory.findAllByMeal(mealId);
    }
}
