package com.example.healthy.security.entities;
import com.example.healthy.entities.*;
import com.example.healthy.enums.ActivityLevel;
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
    private String username;//username is used at the same time as an email in order to send credentials
    private String password;//
    private int age;//
    private String gender;//
    private String firstName;//
    private String lastName;//
    private String healthGoals;//
    private String name;
    private String goals;
    private Double weight;//
    private Double height;//
    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;
    private Double bmr;
    private Double maxDailyCalories;


    public void setBmr() {
        System.err.println("setting BMR");

        if(this.weight != null && this.height != null && this.height > 0 && this.weight > 0){
            if(this.gender.toLowerCase().equals("men")){
                //BMR=10×weight (kg)+6.25×height (cm)−5×age (years)+5
                this.bmr = 10*this.weight+6.25*this.height-5*this.age+5;
            }else if(this.gender.toLowerCase().equals("women")){
                //BMR=10×weight (kg)+6.25×height (cm)−5×age (years)−161
                this.bmr = 10*this.weight+6.25*this.height-5*this.age-161;
            }
        }
    }

    @OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
    private List<Report> reports;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles= new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Meal> meals;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DailyMeal> dailyMeals;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<HealthData> healthData;

    @OneToOne(cascade = CascadeType.ALL)
    private MedicalRecord medicalRecord;

    public List<Role> getRoles() {
        return roles;
    }

    public User(String email,String password) {
        this.username = email;
        this.password = password;
    }

    public void setMaxDailyCalories() {
        System.err.println("setting max daily calories");
        this.setBmr();
        if(activityLevel!=null && this.bmr != null){
            switch (activityLevel){
                case Sedentary -> maxDailyCalories = this.bmr*1.2;
                case LightlyActive -> this.maxDailyCalories = this.bmr*1.375;
                case ModeratelyActive -> this.maxDailyCalories = this.bmr*1.55;
                case VeryActive -> this.maxDailyCalories = this.bmr*1.725;
                case SuperActive -> this.maxDailyCalories=this.bmr*1.9;

            }
        }
    }
}
