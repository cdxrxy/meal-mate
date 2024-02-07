package com.example.mealmate.dto.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeSearchResponse {
    private Integer offset;
    private Integer number;
    private List<RecipeSearch> results;
    private Integer totalResults;
}
