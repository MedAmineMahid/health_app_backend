package com.example.healthy.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class MealServiceImpl implements MealService {
    @Value("${x-app-id}")
    private String appId;
    @Value("${x-app-key}")
    private String apiKey;
    @Override
    public String calculateMealCalories(String meal) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-app-id", appId);
        headers.set("x-app-key",apiKey);
        headers.set("Content-Type", "application/json");

        Map<String, String> body = new HashMap<>();
        body.put("query", meal);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body,headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.exchange(
                "https://trackapi.nutritionix.com/v2/natural/nutrients", HttpMethod.POST,request,String.class);
        return res.getBody();
    }
    @Override
    public String getIngredien(String name){
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-app-id", appId);
        headers.set("x-app-key",apiKey);
        headers.set("Content-Type", "application/json");

        Map<String, String> body = new HashMap<>();
        body.put("query", name);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(body,headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.exchange(
                "https://trackapi.nutritionix.com/v2/search/instant/?query="+name, HttpMethod.GET,request,String.class);
        return res.getBody();
    }
}
