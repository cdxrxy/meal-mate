package com.example.mealmate.model;

import com.example.mealmate.enums.RoleType;
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
    private Boolean isActive;
    @OneToMany(mappedBy = "user")
    private List<Meal> meals;
}
