package com.codewithzenas.store.mappers;

import com.codewithzenas.store.dtos.CartDto;
import com.codewithzenas.store.dtos.CartItemDto;
import com.codewithzenas.store.entities.Cart;
import com.codewithzenas.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "items", source = "cartItems")
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto  toDto(CartItem cartItem);
}
