package com.example.healthy.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double quantity;
    private String measure;
    private double calories;
    @ManyToOne
    @JoinColumn(name = "daily_meal_id")
    @JsonIgnore // Prevent infinite recursion
    private DailyMeal dailyMeal;
    public Ingredient(String name, double quantity, String measure, double calories) {
        this.name = name;
        this.quantity = quantity;
        this.measure = measure;
        this.calories = calories;
    }
}
