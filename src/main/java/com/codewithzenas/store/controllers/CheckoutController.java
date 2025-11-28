package com.codewithzenas.store.controllers;

import com.codewithzenas.store.dtos.CheckoutRequestDto;
import com.codewithzenas.store.dtos.ErrorDto;
import com.codewithzenas.store.exceptions.CartEmptyException;
import com.codewithzenas.store.exceptions.CartNotFoundException;
import com.codewithzenas.store.services.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<?> checkout(@Valid @RequestBody() CheckoutRequestDto request) {
        return ResponseEntity.ok(checkoutService.checkout(request));
    }

    @ExceptionHandler({CartNotFoundException.class, CartEmptyException.class})
    public ResponseEntity<ErrorDto> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(exception.getMessage()));
    }
}
