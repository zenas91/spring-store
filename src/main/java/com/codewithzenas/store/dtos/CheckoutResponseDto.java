package com.codewithzenas.store.dtos;

import lombok.Data;

@Data
public class CheckoutResponseDto {
    private Long orderId;

    public CheckoutResponseDto(Long id) {
        this.orderId = id;
    }
}

