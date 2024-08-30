package com.example.healthy.repositories;

import com.example.healthy.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientReposiory extends JpaRepository<Ingredient, Long> {

    @Query(
            value = "SELECT * FROM ingredient WHERE  daily_meal_id = ?1 ",
            nativeQuery = true
    )
    List<Ingredient> findAllByMeal(Long meal_id);
}
