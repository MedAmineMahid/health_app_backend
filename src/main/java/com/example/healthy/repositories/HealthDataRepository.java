package com.example.healthy.repositories;

import com.example.healthy.entities.Activity;
import com.example.healthy.entities.HealthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthDataRepository extends JpaRepository<HealthData, Long> {

}
