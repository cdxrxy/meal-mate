package com.example.mealmate.service;

import com.example.mealmate.dto.Login;
import com.example.mealmate.dto.Token;
import com.example.mealmate.security.internal.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternalAuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public Token authenticate(Login login) {
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
}
