package com.example.mealmate.dto.ingredient;

import com.example.mealmate.enums.UnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientInformationCriteria {
    private Float amount;
    private UnitType unit;
}