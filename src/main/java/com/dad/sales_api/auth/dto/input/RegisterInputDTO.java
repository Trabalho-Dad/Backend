package com.dad.sales_api.auth.dto.input;

import com.dad.sales_api.auth.dto.request.RegisterRequestDTO;

public record RegisterInputDTO(
    String name,
    String email,
    String password,
    String cpf
) {

    public RegisterInputDTO(RegisterRequestDTO input) {
        this(
            input.name(),
            input.email(),
            input.password(),
            input.cpf()
        );
    }
}