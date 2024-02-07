package com.example.mealmate.util;

import com.example.mealmate.dto.UserProfile;
import com.example.mealmate.model.User;

public class UserUtil {
    public static UserProfile userToProfile(User user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(user.getId());
        userProfile.setEmail(user.getEmail());
        userProfile.setUsername(user.getUsername());
        userProfile.setRole(user.getRole());
        userProfile.setType(user.getType());
        userProfile.setIsActive(user.getIsActive());
        return userProfile;
    }
}
