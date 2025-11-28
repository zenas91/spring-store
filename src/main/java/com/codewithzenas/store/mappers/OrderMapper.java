package com.codewithzenas.store.mappers;

import com.codewithzenas.store.dtos.OrderDto;
import com.codewithzenas.store.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "items", source = "orderItems")
    OrderDto toDto(Order order);

}
