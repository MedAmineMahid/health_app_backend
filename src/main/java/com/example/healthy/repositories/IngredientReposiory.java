package com.example.healthy.repositories;

import com.example.healthy.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientReposiory extends JpaRepository<Ingredient, Long> {

}
