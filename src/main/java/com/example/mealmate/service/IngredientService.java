package com.example.mealmate.service;

import com.example.mealmate.client.IngredientClient;
import com.example.mealmate.dto.ingredient.Ingredient;
import com.example.mealmate.dto.ingredient.IngredientCriteria;
import com.example.mealmate.dto.ingredient.IngredientInformationCriteria;
import com.example.mealmate.dto.ingredient.IngredientSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientClient ingredientClient;

    public IngredientSearchResponse getIngredientsByCriteria(IngredientCriteria ingredientCriteria) {
        return ingredientClient.searchIngredients(ingredientCriteria);
    }

    public Ingredient getIngredientInformation(Long id,
                                               IngredientInformationCriteria ingredientInformationCriteria) {
        return ingredientClient.getIngredientInformation(id, ingredientInformationCriteria);
    }
}
