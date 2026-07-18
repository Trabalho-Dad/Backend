package com.dad.sales_api.user.dto.output;

import com.dad.sales_api.shared.helpers.NormalizeOutput;

public record UpdateUserOutputDTO(
    Integer id,
    String name,
    String cpf,
    String email
) {
  public UpdateUserOutputDTO(Integer id, String name, String cpf, String email) {
    this.id = id;
    this.cpf = NormalizeOutput.cpf(cpf);
    this.name = NormalizeOutput.name(name);
    this.email = email;
  }
}
