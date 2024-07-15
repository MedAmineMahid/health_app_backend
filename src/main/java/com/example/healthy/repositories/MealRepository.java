package com.example.healthy.repositories;

import com.example.healthy.entities.Activity;
import com.example.healthy.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

}
