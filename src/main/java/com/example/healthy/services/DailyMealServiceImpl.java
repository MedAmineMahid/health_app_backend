package com.example.healthy.services;

import com.example.healthy.entities.DailyMeal;
import com.example.healthy.repositories.DailyMealRepository;
import com.example.healthy.security.services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class DailyMealServiceImpl implements DailyMealService {
    @Autowired
    private DailyMealRepository dailyMealRepository;

    @Override
    public DailyMeal addDailyMeal(DailyMeal dailyMeal) {
        return dailyMealRepository.save(dailyMeal);
    }

    @Autowired
    UserDetailServiceImpl userDetailService;
    @Override
    public List<DailyMeal> getDailyMeals() {
        String userId = userDetailService.getLoggedUser().getUserId();
        System.out.println(userId);
        //String userId = "7bef958f-945f-4e98-b858-bc9151a65a2b";
        LocalDate localDate = LocalDate.now();
        System.out.println("Current date: " + localDate);
        return dailyMealRepository.findAllByIdUserAndDate(userId,localDate);
    }

    @Override
    public DailyMeal getDailyMealById(Long id) {
        return dailyMealRepository.findById(id).get();
    }

    @Override
    public DailyMeal updateDailyMeal(DailyMeal dailyMeal) {
        return dailyMealRepository.save(dailyMeal);
    }

    @Override
    public void deleteDailyMeal(Long id) {
dailyMealRepository.deleteById(id);
    }
}
