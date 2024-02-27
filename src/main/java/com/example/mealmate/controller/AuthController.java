package com.example.mealmate.controller;

import com.example.mealmate.dto.Login;
import com.example.mealmate.dto.Register;
import com.example.mealmate.dto.Token;
import com.example.mealmate.dto.UserProfile;
import com.example.mealmate.enums.UserType;
import com.example.mealmate.mapper.UserMapper;
import com.example.mealmate.service.InternalAuthService;
import com.example.mealmate.service.UserService;
import com.example.mealmate.util.AuthenticationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Авторизация")
@RestController
@RequestMapping("/api/meal-mate/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final InternalAuthService internalAuthService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final OAuth2AuthorizedClientRepository repository;

    @GetMapping("/token")
    public String token(Authentication authentication, HttpServletRequest request) {
        if (authentication instanceof OAuth2AuthenticationToken token) {
            String clientId = token.getAuthorizedClientRegistrationId();
            OAuth2AuthorizedClient client = repository.loadAuthorizedClient(clientId, token, request);
            return client.getAccessToken().getTokenValue();
        }

        throw new IllegalArgumentException("Authentication must be of OAuth2AuthenticationToken type");
    }

    @Operation(summary = "Войти с помощью логина и пароля")
    @PostMapping("/login")
    public Token login(@RequestBody @Valid Login login) {
        return internalAuthService.authenticate(login);
    }

    @Operation(summary = "Зарегистрироваться с помощью логина и пароля")
    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Token register(@RequestBody @Valid Register register) {
        userService.register(userMapper.registerToUser(register));
        return internalAuthService.authenticate(new Login(register.getEmail(), register.getPassword()));
    }

    @Operation(summary = "Получить ифнормацию о текущем пользователе")
    @GetMapping("/me")
    public UserProfile getCurrentUser(Authentication authentication) {
        String email = AuthenticationUtil.extractEmail(authentication);
        UserType type = AuthenticationUtil.extractType(authentication);

        return userMapper.userToProfile(
                userService.getUserByEmailAndType(email, type)
        );
    }
}
