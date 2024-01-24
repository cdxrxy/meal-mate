package com.example.mealmate.security.internal;

import com.example.mealmate.enums.UserType;
import com.example.mealmate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternalUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.mealmate.model.User user = userRepository
                .findByEmailAndType(username, UserType.INTERNAL_USER)
                .orElseThrow(() -> new UsernameNotFoundException("User with such username was not found"));

        return new User(
                user.getEmail(),
                user.getPassword(),
                user.getIsActive(),
                user.getIsActive(),
                user.getIsActive(),
                user.getIsActive(),
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
