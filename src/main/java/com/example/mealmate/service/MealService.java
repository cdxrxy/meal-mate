package com.example.mealmate.service;

import com.example.mealmate.dto.MealCriteria;
import com.example.mealmate.enums.UserType;
import com.example.mealmate.model.Meal;
import com.example.mealmate.model.User;
import com.example.mealmate.repository.MealRepository;
import com.example.mealmate.repository.specification.MealSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;
    private final UserService userService;

    @Transactional
    public void saveMeal(Meal meal, String email, UserType type) {
        User user = userService.getUserByEmailAndType(email, type);
        meal.setUser(user);
        mealRepository.save(meal);
    }

    @Transactional(readOnly = true)
    public List<Meal> getMealsByCriteria(String email, UserType type, MealCriteria mealCriteria) {
        return mealRepository.findAll(
                MealSpecification.hasEmailAndType(email, type)
                        .and(MealSpecification.hasName(mealCriteria.getName()))
                        .and(MealSpecification.hasDateFrom(mealCriteria.getDateFrom()))
                        .and(MealSpecification.hasDateTo(mealCriteria.getDateTo())),
                Sort.by("id")
        );
    }
}
