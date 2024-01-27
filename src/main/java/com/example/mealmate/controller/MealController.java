package com.example.mealmate.controller;

import com.example.mealmate.dto.MealCriteria;
import com.example.mealmate.enums.UserType;
import com.example.mealmate.model.Meal;
import com.example.mealmate.service.MealService;
import com.example.mealmate.util.AuthenticationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Питание")
@RestController
@RequestMapping("/api/meal-mate/v1/meal")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @Operation(summary = "сохранить информацию о приеме пищи текущего пользователя")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void saveMeal(@RequestBody Meal meal, Authentication authentication) {
        String email = AuthenticationUtil.extractEmail(authentication);
        UserType type = AuthenticationUtil.extractType(authentication);

        mealService.saveMeal(meal, email, type);
    }

    @Operation(summary = "получить информацию о приемах пищи текущего пользователя")
    @GetMapping
    public List<Meal> getMealsByCriteria(Authentication authentication, MealCriteria mealCriteria) {
        String email = AuthenticationUtil.extractEmail(authentication);
        UserType type = AuthenticationUtil.extractType(authentication);

        return mealService.getMealsByCriteria(email, type, mealCriteria);
    }
}
