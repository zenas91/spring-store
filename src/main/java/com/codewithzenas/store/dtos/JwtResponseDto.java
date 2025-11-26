package com.codewithzenas.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponseDto {
    private String token;
}
