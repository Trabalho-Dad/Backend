package com.dad.sales_api.auth.dto.output;

import com.dad.sales_api.shared.helpers.NormalizeOutput;

public record RegisterOutputDTO(
    Integer id,
    String name,
    String cpf,
    String email
) {
  public RegisterOutputDTO(Integer id, String name, String cpf, String email) {
    this.id = id;
    this.name = NormalizeOutput.name(name);
    this.cpf = NormalizeOutput.cpf(cpf);
    this.email = email;
  }
}