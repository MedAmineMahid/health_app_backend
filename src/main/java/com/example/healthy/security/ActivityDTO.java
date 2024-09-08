package com.example.healthy.security;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ActivityDTO {
    private Long id;
    private String title;
    private String description;
    private int duration;
    private LocalDate time;
    private String userId;
    private String username;
}
