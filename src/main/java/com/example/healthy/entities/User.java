package com.example.healthy.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    private String age;
    private String gender;
    private String goals;

    @OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
    private List<Report> reports;


    @OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
    private List<Meal> meals;

    @OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
    private List<Activity> activities;

    @OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
    private List<HealthData> healthData;

    @OneToOne(mappedBy = "user",fetch=FetchType.LAZY)
    private MedicalRecord medicalRecord ;
}
