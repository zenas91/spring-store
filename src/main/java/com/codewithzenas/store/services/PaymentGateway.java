package com.codewithzenas.store.services;

import com.codewithzenas.store.dtos.PaymentResult;
import com.codewithzenas.store.dtos.WebhookRequest;
import com.codewithzenas.store.entities.Order;

import java.util.Optional;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);

    Optional<PaymentResult> parseWebhookRequest(WebhookRequest request);
}
