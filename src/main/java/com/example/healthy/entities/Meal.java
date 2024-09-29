package com.example.healthy.entities;

import com.example.healthy.security.entities.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mealName;
    @ElementCollection
    private List<String> ingredients = new ArrayList<>(); // Use ElementCollection to store a list of strings
    private int totalCalories;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    private LocalDateTime time;

    // Getters and setters


}
