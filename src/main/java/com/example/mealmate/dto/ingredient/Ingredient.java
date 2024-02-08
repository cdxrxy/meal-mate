package com.example.mealmate.dto.ingredient;

import com.example.mealmate.dto.Nutrition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private Long id;
    private String name;
    private Float amount;
    private String unitLong;
    private Nutrition nutrition;
}
