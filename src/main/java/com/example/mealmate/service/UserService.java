package com.example.mealmate.service;

import com.example.mealmate.enums.RoleType;
import com.example.mealmate.enums.UserType;
import com.example.mealmate.model.User;
import com.example.mealmate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public User getUserByEmailAndType(String email, UserType type) {
        return userRepository.findByEmailAndType(email, type).orElseThrow();
    }

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleType.ROLE_USER);
        user.setType(UserType.INTERNAL_USER);
        user.setIsActive(true);
        userRepository.save(user);
    }
}
