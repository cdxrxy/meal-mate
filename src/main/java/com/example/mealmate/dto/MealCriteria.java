package com.example.mealmate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealCriteria {
    private String name;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
