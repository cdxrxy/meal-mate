package com.example.mealmate.model;

import com.example.mealmate.enums.RoleType;
import com.example.mealmate.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @JsonIgnore
    @ToString.Exclude
    private String password;
    private String username;
    @Enumerated(EnumType.STRING)
    private RoleType role;
    @Enumerated(EnumType.STRING)
    private UserType type;
    private Boolean isActive;
    @OneToMany(mappedBy = "user")
    private List<Meal> meals;
    @OneToMany(mappedBy = "user")
    private List<RecipeMeal> recipeMeals;
}
