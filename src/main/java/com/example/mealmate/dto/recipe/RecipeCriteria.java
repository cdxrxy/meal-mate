package com.example.mealmate.dto.recipe;

import com.example.mealmate.enums.CuisineType;
import com.example.mealmate.enums.DietType;
import com.example.mealmate.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCriteria {
    private String query;
    private CuisineType cuisine;
    private DietType diet;
    private MealType type;
    private String includeIngredients;
    private String excludeIngredients;
    private Integer minCalories;
    private Integer maxCalories;
    private Integer minProtein;
    private Integer maxProtein;
    private Integer minFat;
    private Integer maxFat;
    private Integer minCarbs;
    private Integer maxCarbs;
    private Integer maxReadyTime;
    private Integer offset;
    private Integer number;
}
