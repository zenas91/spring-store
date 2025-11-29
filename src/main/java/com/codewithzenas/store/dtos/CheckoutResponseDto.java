package com.codewithzenas.store.dtos;

import lombok.Data;

@Data
public class CheckoutResponseDto {
    private Long orderId;
    private String checkoutUrl;

    public CheckoutResponseDto(Long id, String checkoutUrl) {
        this.orderId = id;
        this.checkoutUrl = checkoutUrl;
    }
}

