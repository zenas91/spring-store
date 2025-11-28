package com.codewithzenas.store.services;

import com.codewithzenas.store.dtos.CheckoutRequestDto;
import com.codewithzenas.store.dtos.CheckoutResponseDto;
import com.codewithzenas.store.dtos.ErrorDto;
import com.codewithzenas.store.entities.Order;
import com.codewithzenas.store.exceptions.CartEmptyException;
import com.codewithzenas.store.exceptions.CartNotFoundException;
import com.codewithzenas.store.repositories.CartRepository;
import com.codewithzenas.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CheckoutService {

    private final CartRepository cartRepository;
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final CartService cartService;

    public CheckoutResponseDto  checkout(CheckoutRequestDto requestDto) {
        var cart = cartRepository.getCartWithItems(requestDto.getCartId()).orElse(null);
        if (cart == null)
            throw new CartNotFoundException();

        if (cart.isEmpty()){
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart, authService.getLoggedUser());

        orderRepository.save(order);
        cartService.clearCart(cart.getId());

        return new CheckoutResponseDto(order.getId());
    }
}
