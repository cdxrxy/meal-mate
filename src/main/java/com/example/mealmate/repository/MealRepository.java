package com.example.mealmate.repository;

import com.example.mealmate.model.Meal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long>, JpaSpecificationExecutor<Meal> {
    @EntityGraph(attributePaths = "user")
    List<Meal> findAll(Specification<Meal> specification, Sort sort);
}
