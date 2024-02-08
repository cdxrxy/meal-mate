package com.example.mealmate.dto.ingredient;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCriteria {
    @NotBlank(message = "Empty query")
    private String query;
    private Integer offset;
    private Integer number;
}
