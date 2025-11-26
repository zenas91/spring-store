package com.codewithzenas.store.mappers;

import com.codewithzenas.store.dtos.AuthDto;
import com.codewithzenas.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthDto toDto(User user);
}
