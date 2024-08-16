
package com.example.healthy.repositories;

import com.example.healthy.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    // Method to find all activities associated with a specific MedicalRecord
    List<Activity> findByMedicalRecordId(Long medicalRecordId);

    // Method to find an activity by its ID and associated MedicalRecord ID
    Optional<Activity> findByIdAndMedicalRecordId(Long activityId, Long medicalRecordId);
}
