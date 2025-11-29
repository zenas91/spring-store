package com.codewithzenas.store.controllers;

import com.codewithzenas.store.dtos.CheckoutRequestDto;
import com.codewithzenas.store.dtos.ErrorDto;
import com.codewithzenas.store.dtos.WebhookRequest;
import com.codewithzenas.store.exceptions.CartEmptyException;
import com.codewithzenas.store.exceptions.CartNotFoundException;
import com.codewithzenas.store.exceptions.PaymentException;
import com.codewithzenas.store.repositories.OrderRepository;
import com.codewithzenas.store.services.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final OrderRepository orderRepository;



    @PostMapping
    public ResponseEntity<?> checkout(@Valid @RequestBody() CheckoutRequestDto request) {
        return ResponseEntity.ok(checkoutService.checkout(request));
    }

    @PostMapping("/webhook")
    public void handleWebhook(
            @RequestHeader Map<String, String> headers,
            @RequestBody String payload
    ){
        checkoutService.handleWebhookEvent(new WebhookRequest(headers, payload));
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<ErrorDto> handlePaymentException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto("Error while processing checkout"));
    }

    @ExceptionHandler({CartNotFoundException.class, CartEmptyException.class})
    public ResponseEntity<ErrorDto> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(exception.getMessage()));
    }
}
