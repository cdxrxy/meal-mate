package com.example.mealmate.controller;

import com.example.mealmate.dto.ingredient.Ingredient;
import com.example.mealmate.dto.ingredient.IngredientCriteria;
import com.example.mealmate.dto.ingredient.IngredientInformationCriteria;
import com.example.mealmate.dto.ingredient.IngredientSearchResponse;
import com.example.mealmate.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meal-mate/v1/ingredient")
@RequiredArgsConstructor
@Tag(name = "Ингредиенты")
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/search")
    @Operation(summary = "Получить ингредиенты по критериям")
    public IngredientSearchResponse getIngredientsByCriteria(IngredientCriteria ingredientCriteria) {
        return ingredientService.getIngredientsByCriteria(ingredientCriteria);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить полную информацию об ингредиенте по id")
    public Ingredient getIngredientInformationById(@PathVariable Long id,
                                                   IngredientInformationCriteria ingredientInformationCriteria) {
        return ingredientService.getIngredientInformation(id, ingredientInformationCriteria);
    }
}
