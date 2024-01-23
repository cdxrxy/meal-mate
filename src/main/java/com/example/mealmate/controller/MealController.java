package com.example.mealmate.controller;

import com.example.mealmate.model.Meal;
import com.example.mealmate.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal-mate/v1/meal")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void saveMeal(@RequestBody Meal meal, Authentication authentication) {
        mealService.saveMeal(meal, authentication.getName());
    }

    @GetMapping
    public List<Meal> getMeals(Authentication authentication) {
        return mealService.getMealsByEmail(authentication.getName());
    }
}
