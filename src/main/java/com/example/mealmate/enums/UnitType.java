package com.example.mealmate.enums;

public enum UnitType {
    G;

    public String toString() {
        return this.name().replace("_", " ").toLowerCase();
    }
}
