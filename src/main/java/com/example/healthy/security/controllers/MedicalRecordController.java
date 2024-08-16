package com.example.healthy.security.controllers;

import com.example.healthy.security.services.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/medical")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping("/upload/{userId}")
    public ResponseEntity<String> uploadMedicalRecord(@PathVariable String userId,
                                                      @RequestParam("file") MultipartFile file) {
        try {
            medicalRecordService.saveMedicalRecord(userId, file);
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }
}

