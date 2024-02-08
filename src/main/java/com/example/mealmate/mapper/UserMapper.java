package com.example.mealmate.mapper;

import com.example.mealmate.dto.Register;
import com.example.mealmate.dto.UpdateUser;
import com.example.mealmate.dto.UserProfile;
import com.example.mealmate.enums.RoleType;
import com.example.mealmate.enums.UserType;
import com.example.mealmate.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User registerToUser(Register register);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(UpdateUser updateUser, @MappingTarget User user);

    UserProfile userToProfile(User user);

    @AfterMapping
    default void fillUserForRegistration(Register register, @MappingTarget User user) {
        user.setRole(RoleType.ROLE_USER);
        user.setType(UserType.INTERNAL_USER);
        user.setIsActive(true);
    }
}
