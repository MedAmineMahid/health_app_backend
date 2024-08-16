package com.example.healthy.security.services;

import com.example.healthy.entities.MedicalRecord;
import com.example.healthy.repositories.MedicalRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public void saveMedicalRecord(String userId, MultipartFile file) throws IOException {
        Optional<MedicalRecord> recordOpt = medicalRecordRepository.findByUserUserId(userId);

        if (recordOpt.isPresent()) {
            MedicalRecord record = recordOpt.get();
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path path = Paths.get("uploads/" + fileName);

            // Save the file to the file system
            Files.write(path, file.getBytes());

            // Update the MedicalRecord with the file path
            record.setPdfPath(path.toString());

            // Save the updated MedicalRecord entity to the database
            medicalRecordRepository.save(record);
        } else {
            // Handle the case where the MedicalRecord is not found for the user
            // You might want to throw an exception or create a new record
        }
    }
}
