package com.example.healthy.entities;

import com.example.healthy.security.entities.User;
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
public class DailyMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mealCategories;

    private Date date;
    //user
    private String mealName;
    private boolean favorit;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "DailyMeal{" +
                "id=" + id +
                ", mealCategories='" + mealCategories + '\'' +
                ", date=" + date +
                ", mealName='" + mealName + '\'' +
                ", favorit=" + favorit +
                ", ingredients=" + ingredients.toString() +
                ", user=" + user +
                '}';
    }
}
