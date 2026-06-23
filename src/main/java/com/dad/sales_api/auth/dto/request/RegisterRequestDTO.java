package com.dad.sales_api.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(

    @NotBlank
    @Size(max = 150)
    String name,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Size(min = 8, max = 28)
    String password

) {
    
}
