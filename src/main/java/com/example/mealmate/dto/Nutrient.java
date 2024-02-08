package com.example.mealmate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nutrient {
    private String name;
    private Float amount;
    private String unit;
    private Float percentOfDailyNeeds;
}
