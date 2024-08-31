package com.example.healthy.security.entities;

import com.example.healthy.entities.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String userId;
    @Column(unique = true)
    private String username;
    private String password;
    private int age;
    private String gender;
    private String firstName;
    private String lastName;
    private String healthGoals;
    private String name;
    private String goals;

    @OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
    private List<Notification> notifications;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles= new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Meal> meals;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<HealthData> healthData;



    public List<Role> getRoles() {
        return roles;
    }

    public User(String email,String password) {
        this.username = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
