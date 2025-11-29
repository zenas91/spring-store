package com.codewithzenas.store.services;

import com.codewithzenas.store.dtos.CheckoutRequestDto;
import com.codewithzenas.store.dtos.CheckoutResponseDto;
import com.codewithzenas.store.dtos.WebhookRequest;
import com.codewithzenas.store.entities.Order;
import com.codewithzenas.store.exceptions.CartEmptyException;
import com.codewithzenas.store.exceptions.CartNotFoundException;
import com.codewithzenas.store.exceptions.PaymentException;
import com.codewithzenas.store.repositories.CartRepository;
import com.codewithzenas.store.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CheckoutService {

    private final CartRepository cartRepository;
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final PaymentGateway paymentGateway;

    @Transactional
    public CheckoutResponseDto  checkout(CheckoutRequestDto requestDto) {
        var cart = cartRepository.getCartWithItems(requestDto.getCartId()).orElse(null);
        if (cart == null)
            throw new CartNotFoundException();

        if (cart.isEmpty()){
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart, authService.getLoggedUser());
        orderRepository.save(order);

        try {
            var session = paymentGateway.createCheckoutSession(order);
            cartService.clearCart(cart.getId());
            return new CheckoutResponseDto(order.getId(), session.getCheckoutUrl());
        }
        catch (PaymentException e) {
            orderRepository.delete(order);
            throw e;
        }
    }

    public void handleWebhookEvent(WebhookRequest requestDto) {
        paymentGateway.parseWebhookRequest(requestDto)
                .ifPresent(paymentResult -> {
                    var order = orderRepository.findById(paymentResult.getOrderId()).orElseThrow();
                    order.setStatus(paymentResult.getPaymentStatus());
                    orderRepository.save(order);
                });
    }
}
