package com.example.mealmate.dto;

import com.example.mealmate.enums.RoleType;
import com.example.mealmate.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private Long id;
    private String email;
    private String username;
    private RoleType role;
    private UserType type;
    private Boolean isActive;
}
