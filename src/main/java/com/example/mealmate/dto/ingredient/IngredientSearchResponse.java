package com.example.mealmate.dto.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientSearchResponse {
    private List<IngredientSearch> results;
    private Integer offset;
    private Integer number;
    private Integer totalResults;
}
