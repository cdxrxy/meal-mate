package com.example.mealmate.mapper;

import com.example.mealmate.dto.Register;
import com.example.mealmate.enums.RoleType;
import com.example.mealmate.enums.UserType;
import com.example.mealmate.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User registerToUser(Register register);

    @AfterMapping
    default void fillUserForRegistration(Register register, @MappingTarget User user) {
        user.setRole(RoleType.ROLE_USER);
        user.setType(UserType.INTERNAL_USER);
        user.setIsActive(true);
    }
}
