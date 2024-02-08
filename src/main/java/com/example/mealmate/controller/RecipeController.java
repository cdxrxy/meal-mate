package com.example.mealmate.controller;

import com.example.mealmate.dto.RecipeMealCriteria;
import com.example.mealmate.dto.UserRecipeMeal;
import com.example.mealmate.dto.Nutrition;
import com.example.mealmate.dto.recipe.Recipe;
import com.example.mealmate.dto.recipe.RecipeCriteria;
import com.example.mealmate.dto.recipe.RecipeSearchResponse;
import com.example.mealmate.enums.UserType;
import com.example.mealmate.service.RecipeService;
import com.example.mealmate.util.AuthenticationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal-mate/v1/recipe")
@RequiredArgsConstructor
@Tag(name = "Рецепты")
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping("/search")
    @Operation(summary = "Получить рецепты по критериям")
    public RecipeSearchResponse getRecipesByCriteria(RecipeCriteria recipeCriteria) {
        return recipeService.searchRecipes(recipeCriteria);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить полную информацию о рецепте по id")
    public Recipe getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping("/{id}/nutrition")
    @Operation(summary = "Получить полную информацию о составе рецепта по id")
    public Nutrition getNutritionByRecipeId(@PathVariable Long id) {
        return recipeService.getNutritionByRecipeId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Сохранить информацию о приеме пищи через рецепт для текущего пользователя")
    public void saveUserRecipeMeal(Authentication authentication,
                                   @RequestBody UserRecipeMeal userRecipeMeal) {
        String email = AuthenticationUtil.extractEmail(authentication);
        UserType type = AuthenticationUtil.extractType(authentication);

        recipeService.saveUserRecipeMeal(email, type, userRecipeMeal);
    }

    @GetMapping
    @Operation(summary = "Получить информацию о приемах пищи через рецепт для текущего пользователя")
    public List<Recipe> getUserRecipeMeal(RecipeMealCriteria recipeMealCriteria,
                                          Authentication authentication) {
        String email = AuthenticationUtil.extractEmail(authentication);
        UserType type = AuthenticationUtil.extractType(authentication);

        return recipeService.getUserRecipeMeal(recipeMealCriteria, email, type);
    }
}
