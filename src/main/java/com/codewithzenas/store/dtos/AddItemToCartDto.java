package com.codewithzenas.store.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddItemToCartDto {
    @NotBlank(message = "Product Id is required")
    private Long productId;
}
