package com.codewithzenas.store.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthDto {
    @NotNull(message = "Email must be provided")
    @Email
    private String email;

    @NotBlank(message = "Password must be provided")
    private String password;
}
