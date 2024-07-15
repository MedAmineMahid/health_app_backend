package com.example.healthy.services;

import com.example.healthy.entities.HealthData;
import com.example.healthy.repositories.HealthDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HealthDataServiceImpl implements HealthDataService{
    private HealthDataRepository healthDataRepository;
    @Override
    public HealthData saveAdhesion(HealthData healthData) {
        return healthDataRepository.save(healthData);
    }

    @Override
    public HealthData updateAdhesion(HealthData healthData) {
        return healthDataRepository.save(healthData);
    }

    @Override
    public void deleteHealthDataById(Long id) {
        healthDataRepository.deleteById(id);

    }

    @Override
    public void deleteAllHealthData() {
        healthDataRepository.deleteAll();
    }

    @Override
    public HealthData getHealthDataById(Long id) {
        return healthDataRepository.findById(id).get();
    }

    @Override
    public List<HealthData> getAllHealthData() {
        return healthDataRepository.findAll();
    }
}
