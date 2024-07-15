package com.example.healthy.entities;

import com.example.healthy.security.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;
@AllArgsConstructor
@NoArgsConstructor

@Builder
@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hereditaryDiseases;
    private String healthHistory;

    @OneToOne(mappedBy = "medicalRecord",fetch=FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "medicalRecort",fetch=FetchType.LAZY)
    private List<Meal> meals;

    @OneToMany(mappedBy = "medicalRecort",fetch=FetchType.LAZY)
    private List<Activity> activities;

    @OneToMany(mappedBy = "medicalRecort",fetch=FetchType.LAZY)
    private List<HealthData> healthData;

}
