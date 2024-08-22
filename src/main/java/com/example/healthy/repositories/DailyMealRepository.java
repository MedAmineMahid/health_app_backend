package com.example.healthy.repositories;

import com.example.healthy.entities.DailyMeal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyMealRepository extends JpaRepository<DailyMeal, Long> {
}
