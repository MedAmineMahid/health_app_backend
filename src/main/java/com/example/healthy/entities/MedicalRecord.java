package com.example.healthy.entities;

import com.example.healthy.security.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private int age;
    private String goals;
    private String weeklyActivity;

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    private String pdfPath;

    @Lob
    private byte[] medicalPdf;

    @OneToOne(mappedBy = "medicalRecord", fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "medicalRecord", fetch = FetchType.LAZY)
    private List<Meal> meals;

    @OneToMany(mappedBy = "medicalRecord", fetch = FetchType.LAZY)
    private List<Activity> activities;

    @OneToMany(mappedBy = "medicalRecord", fetch = FetchType.LAZY)
    private List<HealthData> healthData;


    // Getters and Setters
}
