package com.example.mealmate.util;

import com.example.mealmate.enums.UserType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class AuthenticationUtil {
    public static String extractEmail(Authentication authentication) {
        if (authentication.getPrincipal() instanceof OAuth2User) {
            return (String) ((OAuth2User) authentication.getPrincipal()).getAttributes().get("email");
        }

        return authentication.getName();
    }

    public static UserType extractType(Authentication authentication) {
        if (authentication.getPrincipal() instanceof OAuth2User) {
            return UserType.OAUTH2_USER;
        }

        return UserType.INTERNAL_USER;
    }
}
