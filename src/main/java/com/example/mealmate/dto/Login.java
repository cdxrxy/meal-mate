package com.example.mealmate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    @Email(message = "Invalid email")
    @NotBlank(message = "Empty email")
    private String email;
    @NotBlank(message = "Empty password")
    private String password;
}
