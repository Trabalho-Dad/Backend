package com.dad.sales_api.auth.dto.input;

import com.dad.sales_api.auth.dto.request.RegisterRequestDTO;
import com.dad.sales_api.shared.helpers.NormalizeInput;

public record RegisterInputDTO(
    String name,
    String email,
    String password,
    String cpf
) {

    public RegisterInputDTO(RegisterRequestDTO input) {
        this(
            NormalizeInput.name(input.name()),
            NormalizeInput.email(input.email()),
            NormalizeInput.password(input.password()),
            NormalizeInput.cpf(input.cpf())
        );
    }
}