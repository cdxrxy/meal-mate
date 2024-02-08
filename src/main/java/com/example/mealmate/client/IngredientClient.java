package com.example.mealmate.client;

import com.example.mealmate.dto.ingredient.Ingredient;
import com.example.mealmate.dto.ingredient.IngredientCriteria;
import com.example.mealmate.dto.ingredient.IngredientInformationCriteria;
import com.example.mealmate.dto.ingredient.IngredientSearchResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class IngredientClient {
    @Value("${app.nutrition.api.key}")
    private String apiKey;
    @Value("${app.nutrition.api.url}")
    private String apiUrl;
    private final RestTemplate restTemplate;

    public IngredientSearchResponse searchIngredients(IngredientCriteria ingredientCriteria) {
        String endpoint = UriComponentsBuilder
                .fromUriString(apiUrl + "/search")
                .queryParam("apiKey", apiKey)
                .queryParams(buildSearchIngredientsParams(ingredientCriteria))
                .build()
                .toUriString();

        return restTemplate.getForObject(endpoint, IngredientSearchResponse.class);
    }

    public Ingredient getIngredientInformation(Long id,
                                               IngredientInformationCriteria ingredientInformationCriteria) {
        String endpoint = UriComponentsBuilder
                .fromUriString(apiUrl + "/" + id + "/information")
                .queryParam("apiKey", apiKey)
                .queryParams(buildIngredientInformationParams(ingredientInformationCriteria))
                .build()
                .toUriString();

        return restTemplate.getForObject(endpoint, Ingredient.class);
    }

    private MultiValueMap<String, String> buildIngredientInformationParams(IngredientInformationCriteria ingredientInformationCriteria) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        if (ingredientInformationCriteria.getAmount() != null) {
            params.add("amount", String.valueOf(ingredientInformationCriteria.getAmount()));
        }
        if (ingredientInformationCriteria.getUnit() != null) {
            params.add("unit", ingredientInformationCriteria.getUnit().toString());
        }

        return params;
    }

    private MultiValueMap<String, String> buildSearchIngredientsParams(IngredientCriteria ingredientCriteria) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        if (StringUtils.hasText(ingredientCriteria.getQuery())) {
            params.add("query", ingredientCriteria.getQuery());
        }
        if (ingredientCriteria.getOffset() != null) {
            params.add("offset", String.valueOf(ingredientCriteria.getOffset()));
        }
        if (ingredientCriteria.getNumber() != null) {
            params.add("number", String.valueOf(ingredientCriteria.getNumber()));
        }

        return params;
    }

    @PostConstruct
    public void init() {
        this.apiUrl += "/food/ingredients";
    }
}
