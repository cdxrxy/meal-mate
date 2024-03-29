package com.example.mealmate.enums;

public enum CuisineType {
    AFRICAN,
    ASIAN,
    AMERICAN,
    BRITISH,
    CAJUN,
    CARIBBEAN,
    CHINESE,
    EASTERN_EUROPEAN,
    EUROPEAN,
    FRENCH,
    GERMAN,
    GREEK,
    INDIAN,
    IRISH,
    ITALIAN,
    JAPANESE,
    JEWISH,
    KOREAN,
    LATIN_AMERICAN,
    MEDITERRANEAN,
    MEXICAN,
    MIDDLE_EASTERN,
    NORDIC,
    SOUTHERN,
    SPANISH,
    THAI,
    VIETNAMESE;

    public String toString() {
        return this.name().replace("_", " ").toLowerCase();
    }
}
