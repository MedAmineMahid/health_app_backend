package com.example.healthy.services;

import com.example.healthy.entities.HealthData;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface HealthDataService {
    HealthData saveAdhesion(HealthData healthData);
    HealthData updateAdhesion(HealthData healthData);
    void deleteHealthDataById(Long id);
    void deleteAllHealthData();
    HealthData getHealthDataById(Long id);
    List<HealthData> getAllHealthData();
}
