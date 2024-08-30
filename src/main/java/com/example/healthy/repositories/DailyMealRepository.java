package com.example.healthy.repositories;

import com.example.healthy.entities.DailyMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DailyMealRepository extends JpaRepository<DailyMeal, Long> {
    @Query(
            value = "select * from daily_meal where user_id=?1 and Date(date) = ?2",
            nativeQuery = true
    )
    List<DailyMeal> findAllByIdUserAndDate(String userId, LocalDate date);
}
