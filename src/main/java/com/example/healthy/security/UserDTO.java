package com.example.healthy.security;

import lombok.Data;
import java.util.List;

@Data
public class UserDTO {
    private String userId;
    private String username;
    private int age;
    private String gender;
    private String name;

    private String healthGoals;
    private String goals;
    private List<ActivityDTO> activities;
}
