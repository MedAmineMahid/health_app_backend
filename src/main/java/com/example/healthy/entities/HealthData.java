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
public class HealthData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int heartbeat;
    private int bloodPressureSystolic;
    private int bloodPressureDiastolic;
    private int oxygenLevel;
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "medicalRecord_id")
    private MedicalRecord medicalRecord;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report ;

}