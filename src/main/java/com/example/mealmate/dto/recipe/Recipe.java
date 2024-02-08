package com.example.mealmate.dto.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private Long id;
    private String title;
    private String image;
    private Integer servings;
    private Integer readyInMinutes;
    private Float healthScore;
    private Float spoonacularScore;
    private Float pricePerServing;
    private List<StepWrapper> analyzedInstructions;
    private Boolean cheap;
    private List<String> cuisines;
    private Boolean dairyFree;
    private List<String> diets;
    private Boolean glutenFree;
    private Boolean ketogenic;
    private Boolean lowFodmap;
    private Boolean sustainable;
    private Boolean vegan;
    private Boolean vegetarian;
    private Boolean veryHealthy;
    private Boolean veryPopular;
    private Boolean whole30;
    private List<String> dishTypes;
    private List<RecipeIngredient> extendedIngredients;
    private String summary;
}
