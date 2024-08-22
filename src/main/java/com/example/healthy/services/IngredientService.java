package com.example.healthy.services;

import com.example.healthy.entities.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface IngredientService {
    public Ingredient addIngredient(Ingredient ingredient);
    public List<Ingredient> findAll();
    public Ingredient findById(Long id);
    public Ingredient update(Ingredient ingredient);
    public void deleteById(Long id);

}
