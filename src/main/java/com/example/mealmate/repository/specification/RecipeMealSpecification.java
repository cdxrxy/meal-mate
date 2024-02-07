package com.example.mealmate.repository.specification;

import com.example.mealmate.enums.UserType;
import com.example.mealmate.model.RecipeMeal;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class RecipeMealSpecification {
    public static Specification<RecipeMeal> hasEmailAndType(String email, UserType type) {
        return (root, query, builder) ->
                builder.and(
                        builder.equal(root.get("user").get("email"), email),
                        builder.equal(root.get("user").get("type"), type)
                );
    }

    public static Specification<RecipeMeal> hasDateFrom(LocalDate dateFrom) {
        return (root, query, builder) ->
                dateFrom == null ?
                        null : builder.greaterThanOrEqualTo(root.get("dateTime"), dateFrom);
    }

    public static Specification<RecipeMeal> hasDateTo(LocalDate dateTo) {
        return (root, query, builder) ->
                dateTo == null ?
                        null : builder.lessThanOrEqualTo(root.get("dateTime"), dateTo);
    }
}
