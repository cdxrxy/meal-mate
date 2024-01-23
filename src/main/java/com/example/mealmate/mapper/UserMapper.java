package com.example.mealmate.mapper;

import com.example.mealmate.dto.Register;
import com.example.mealmate.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User registerToUser(Register register);
}
