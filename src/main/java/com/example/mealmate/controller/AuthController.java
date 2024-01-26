package com.example.mealmate.controller;

import com.example.mealmate.dto.Login;
import com.example.mealmate.dto.Register;
import com.example.mealmate.dto.Token;
import com.example.mealmate.enums.UserType;
import com.example.mealmate.mapper.UserMapper;
import com.example.mealmate.model.User;
import com.example.mealmate.security.internal.JwtService;
import com.example.mealmate.service.UserService;
import com.example.mealmate.util.AuthenticationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Авторизация")
@RestController
@RequestMapping("/api/meal-mate/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "войти с помощью логина и пароля")
    @PostMapping("/login")
    public Token login(@RequestBody Login login) {
        Authentication authentication =
                authenticationManager
                        .authenticate(
                                new UsernamePasswordAuthenticationToken(
                                        login.getEmail(), login.getPassword()
                                )
                        );

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);

        return new Token(jwtService.generateToken(
                login.getEmail(),
                authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .findFirst()
                        .get())
        );
    }

    @Operation(summary = "зарегистрироваться с помощью логина и пароля")
    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@RequestBody Register register) {
        userService.register(userMapper.registerToUser(register));
    }

    @Operation(summary = "получить ифнормацию о текущем пользователе")
    @GetMapping("/me")
    public User getCurrentUser(Authentication authentication) {
        String email = AuthenticationUtil.extractEmail(authentication);
        UserType type = AuthenticationUtil.extractType(authentication);

        return userService.getUserByEmailAndType(email, type);
    }
}
