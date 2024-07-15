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
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String activityType;
    private String intensity;
    private int duration;
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private MedicalRecord medicalRecord;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

}
