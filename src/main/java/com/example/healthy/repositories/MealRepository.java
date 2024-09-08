package com.example.healthy.repositories;

import com.example.healthy.entities.Activity;
import com.example.healthy.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUser_UserId(String userId);
}
