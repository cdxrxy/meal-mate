package com.example.mealmate.dto.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCriteria {
    private String query;
    private Integer offset;
    private Integer number;
}
