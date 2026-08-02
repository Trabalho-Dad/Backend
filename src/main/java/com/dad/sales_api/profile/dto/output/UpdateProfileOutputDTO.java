package com.dad.sales_api.profile.dto.output;

import com.dad.sales_api.shared.helpers.NormalizeOutput;

public record UpdateProfileOutputDTO(
    Integer id,
    String name,
    String cpf,
    String email
) {
  public UpdateProfileOutputDTO(Integer id, String name, String cpf, String email) {
    this.id = id;
    this.cpf = NormalizeOutput.cpf(cpf);
    this.name = NormalizeOutput.name(name);
    this.email = email;
  }
}
