package com.example.mealmate.enums;

public enum MealType {
    MAIN_COURSE,
    SIDE_DISH,
    DESSERT,
    APPETIZER,
    SALAD,
    BREAD,
    BREAKFAST,
    SOUP,
    BEVERAGE,
    SAUCE,
    MARINADE,
    FINGERFOOD,
    SNACK,
    DRINK;

    public String toString() {
        return this.name().replace("_", " ").toLowerCase();
    }
}
