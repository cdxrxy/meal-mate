package com.example.mealmate.client;

import com.example.mealmate.dto.Nutrition;
import com.example.mealmate.dto.recipe.Recipe;
import com.example.mealmate.dto.recipe.RecipeCriteria;
import com.example.mealmate.dto.recipe.RecipeSearchResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeClient {
    @Value("${app.nutrition.api.key}")
    private String apiKey;
    @Value("${app.nutrition.api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate;

    public RecipeSearchResponse searchRecipes(RecipeCriteria recipeCriteria) {
        String endpoint = UriComponentsBuilder
                .fromUriString(apiUrl + "/complexSearch")
                .queryParam("apiKey", apiKey)
                .queryParams(buildSearchRecipesParams(recipeCriteria))
                .build()
                .toUriString();

        return restTemplate.getForObject(endpoint, RecipeSearchResponse.class);
    }

    public Recipe getRecipeById(Long id) {
        String endpoint = UriComponentsBuilder
                .fromUriString(apiUrl + "/" + id + "/information")
                .queryParam("apiKey", apiKey)
                .build()
                .toUriString();

        return restTemplate.getForObject(endpoint, Recipe.class);
    }

    public List<Recipe> getRecipeBulk(List<Long> userRecipeIds) {
        String endpoint = UriComponentsBuilder
                .fromUriString(apiUrl + "/informationBulk")
                .queryParam("apiKey", apiKey)
                .queryParam("ids",
                        userRecipeIds.
                                stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(","))
                )
                .build()
                .toUriString();

        return Arrays.stream(restTemplate.getForObject(endpoint, Recipe[].class)).toList();
    }

    public Nutrition getNutritionByRecipeId(Long id) {
        String endpoint = UriComponentsBuilder
                .fromUriString(apiUrl + "/" + id + "/nutritionWidget.json")
                .queryParam("apiKey", apiKey)
                .build()
                .toUriString();

        return restTemplate.getForObject(endpoint, Nutrition.class);
    }

    private MultiValueMap<String, String> buildSearchRecipesParams(RecipeCriteria recipeCriteria) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        if (StringUtils.hasText(recipeCriteria.getQuery())) {
            params.add("query", recipeCriteria.getQuery());
        }
        if (recipeCriteria.getCuisine() != null) {
            params.add("cuisine", recipeCriteria.getCuisine().toString());
        }
        if (recipeCriteria.getDiet() != null) {
            params.add("diet", recipeCriteria.getDiet().toString());
        }
        if (recipeCriteria.getType() != null) {
            params.add("type", recipeCriteria.getType().toString());
        }
        if (StringUtils.hasText(recipeCriteria.getIncludeIngredients())) {
            params.add("includeIngredients", recipeCriteria.getIncludeIngredients());
        }
        if (StringUtils.hasText(recipeCriteria.getExcludeIngredients())) {
            params.add("excludeIngredients", recipeCriteria.getExcludeIngredients());
        }
        if (recipeCriteria.getMinCalories() != null) {
            params.add("minCalories", String.valueOf(recipeCriteria.getMinCalories()));
        }
        if (recipeCriteria.getMaxCalories() != null) {
            params.add("maxCalories", String.valueOf(recipeCriteria.getMaxCalories()));
        }
        if (recipeCriteria.getMinProtein() != null) {
            params.add("minProtein", String.valueOf(recipeCriteria.getMinProtein()));
        }
        if (recipeCriteria.getMaxProtein() != null) {
            params.add("maxProtein", String.valueOf(recipeCriteria.getMaxProtein()));
        }
        if (recipeCriteria.getMinFat() != null) {
            params.add("minFat", String.valueOf(recipeCriteria.getMinFat()));
        }
        if (recipeCriteria.getMaxFat() != null) {
            params.add("maxFat", String.valueOf(recipeCriteria.getMaxFat()));
        }
        if (recipeCriteria.getMinCarbs() != null) {
            params.add("minCarbs", String.valueOf(recipeCriteria.getMinCarbs()));
        }
        if (recipeCriteria.getMaxCarbs() != null) {
            params.add("maxCarbs", String.valueOf(recipeCriteria.getMaxCarbs()));
        }
        if (recipeCriteria.getMaxReadyTime() != null) {
            params.add("maxReadyTime", String.valueOf(recipeCriteria.getMaxReadyTime()));
        }
        if (recipeCriteria.getOffset() != null) {
            params.add("offset", String.valueOf(recipeCriteria.getOffset()));
        }
        if (recipeCriteria.getNumber() != null) {
            params.add("number", String.valueOf(recipeCriteria.getNumber()));
        }

        return params;
    }

    @PostConstruct
    public void init() {
        this.apiUrl += "/recipes";
    }
}
