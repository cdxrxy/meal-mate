package com.example.mealmate.repository;

import com.example.mealmate.enums.UserType;
import com.example.mealmate.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "meals")
    Optional<User> findByEmailAndType(String email, UserType type);

    boolean existsByEmailAndType(String email, UserType type);
}
