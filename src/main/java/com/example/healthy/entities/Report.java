package com.example.healthy.entities;

import com.example.healthy.security.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "report",fetch=FetchType.LAZY)
    private List<Meal> meals;


    @OneToMany(mappedBy = "report",fetch=FetchType.LAZY)
    private List<HealthData> healthData;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}