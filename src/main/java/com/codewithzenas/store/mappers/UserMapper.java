package com.codewithzenas.store.mappers;

import com.codewithzenas.store.dtos.RegisterUserDto;
import com.codewithzenas.store.dtos.UpdateUserDto;
import com.codewithzenas.store.dtos.UserDto;
import com.codewithzenas.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
    User toUser(RegisterUserDto registerUserDto);
    void updateUser(UpdateUserDto userDto, @MappingTarget User user);
}
