package com.example.healthy.repositories;

import com.example.healthy.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    // Method to find a medical record by user ID
    Optional<MedicalRecord> findByUserUserId(String userId);
}

