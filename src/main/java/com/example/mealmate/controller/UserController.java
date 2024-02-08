package com.example.mealmate.controller;

import com.example.mealmate.dto.UpdateUser;
import com.example.mealmate.enums.UserType;
import com.example.mealmate.service.UserService;
import com.example.mealmate.util.AuthenticationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meal-mate/v1/user")
@RequiredArgsConstructor
@Tag(name = "Пользователи")
public class UserController {
    private final UserService userService;

    @PatchMapping
    @Operation(summary = "Обновить информацию о текущем пользователе")
    public void updateCurrentUser(Authentication authentication,
                                  @RequestBody UpdateUser updateUser) {
        String email = AuthenticationUtil.extractEmail(authentication);
        UserType type = AuthenticationUtil.extractType(authentication);

        userService.updateUserByEmailAndType(email, type, updateUser);
    }
}
