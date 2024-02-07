package com.example.mealmate.repository;

import com.example.mealmate.model.RecipeMeal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeMealRepository extends JpaRepository<RecipeMeal, Long>, JpaSpecificationExecutor<RecipeMeal> {
    @EntityGraph(attributePaths = "user")
    Page<RecipeMeal> findAll(Specification<RecipeMeal> specification, Pageable pageable);
}
