package com.example.healthy.entities;

import com.example.healthy.security.entities.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DailyMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mealCategories;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @PrePersist
    private void onCreate() {
        date = new Date();
    }
    //user
    private String mealName;
    private boolean favorit;


    @OneToMany(mappedBy = "dailyMeal", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Ingredient> ingredients;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public DailyMeal(String mealCategories, String mealName, boolean favorit, List<Ingredient> ingredients) {
        this.mealCategories = mealCategories;
        this.mealName = mealName;
        this.favorit = favorit;
        this.ingredients = ingredients;
    }
}
