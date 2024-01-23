package com.example.mealmate.controller;

import com.example.mealmate.dto.Login;
import com.example.mealmate.dto.Register;
import com.example.mealmate.dto.Token;
import com.example.mealmate.mapper.UserMapper;
import com.example.mealmate.model.User;
import com.example.mealmate.security.JwtService;
import com.example.mealmate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meal-mate/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final UserMapper userMapper;

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

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@RequestBody Register register) {
        userService.register(userMapper.registerToUser(register));
    }

    @GetMapping("/me")
    public User getCurrentUser(Authentication authentication) {
        return userService.getUserByEmail(authentication.getName());
    }
}
