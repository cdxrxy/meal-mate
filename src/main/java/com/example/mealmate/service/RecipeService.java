package com.example.mealmate.service;

import com.example.mealmate.client.RecipeClient;
import com.example.mealmate.dto.RecipeMealCriteria;
import com.example.mealmate.dto.UserRecipeMeal;
import com.example.mealmate.dto.recipe.Nutrition;
import com.example.mealmate.dto.recipe.Recipe;
import com.example.mealmate.dto.recipe.RecipeCriteria;
import com.example.mealmate.dto.recipe.RecipeSearchResponse;
import com.example.mealmate.enums.UserType;
import com.example.mealmate.model.User;
import com.example.mealmate.model.RecipeMeal;
import com.example.mealmate.repository.RecipeMealRepository;
import com.example.mealmate.repository.specification.RecipeMealSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeClient recipeClient;
    private final UserService userService;
    private final RecipeMealRepository recipeMealRepository;

    public RecipeSearchResponse searchRecipes(RecipeCriteria recipeCriteria) {
        return recipeClient.searchRecipes(recipeCriteria);
    }

    @Cacheable("recipe")
    public Recipe getRecipeById(Long id) {
        return recipeClient.getRecipeById(id);
    }

    @Cacheable("nutrition")
    public Nutrition getNutritionByRecipeId(Long id) {
        return recipeClient.getNutritionByRecipeId(id);
    }

    @Transactional
    public void saveUserRecipeMeal(String email, UserType type, UserRecipeMeal userRecipeMeal) {
        User user = userService.getUserByEmailAndType(email, type);

        RecipeMeal recipeMeal = new RecipeMeal();
        recipeMeal.setUser(user);
        recipeMeal.setRecipeId(userRecipeMeal.getRecipeId());
        recipeMeal.setDateTime(userRecipeMeal.getDateTime());

        recipeMealRepository.save(recipeMeal);
    }

    @Transactional(readOnly = true)
    public List<Recipe> getUserRecipeMeal(RecipeMealCriteria recipeMealCriteria,
                                          String email, UserType type) {
        List<Long> userRecipeIds = recipeMealRepository
                .findAll(
                        RecipeMealSpecification.hasEmailAndType(email, type)
                                .and(RecipeMealSpecification.hasDateFrom(recipeMealCriteria.getDateFrom()))
                                .and(RecipeMealSpecification.hasDateTo(recipeMealCriteria.getDateTo())),
                        PageRequest.of(
                                recipeMealCriteria
                                        .getPageNumber() == null ? 0 : recipeMealCriteria.getPageNumber(),
                                recipeMealCriteria
                                        .getPageSize() == null ? 10 : recipeMealCriteria.getPageSize())
                )
                .stream()
                .map(RecipeMeal::getRecipeId)
                .toList();

        return userRecipeIds.isEmpty() ? new ArrayList<>() : recipeClient.getRecipeBulk(userRecipeIds);
    }
}
