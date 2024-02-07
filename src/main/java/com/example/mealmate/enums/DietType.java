package com.example.mealmate.enums;

public enum DietType {
    GLUTEN_FREE,
    KETOGENIC,
    VEGETARIAN,
    VEGAN;

    public String toString() {
        return this.name().replace("_", " ").toLowerCase();
    }
}
