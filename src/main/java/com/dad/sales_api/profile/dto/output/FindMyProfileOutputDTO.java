package com.dad.sales_api.profile.dto.output;

import com.dad.sales_api.shared.enums.RoleEnum;
import com.dad.sales_api.shared.helpers.NormalizeOutput;
import com.dad.sales_api.shared.persistence.postgres.dto.AddressSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.ContactSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.UserEntity;

import java.util.List;

public record FindMyProfileOutputDTO(
    Integer id,
    String name,
    String cpf,
    String email,

    RoleEnum role,
    List<ContactSimpleDTO> contacts,
    List<AddressSimpleDTO> addresses
) {
  public FindMyProfileOutputDTO(UserEntity user, List<ContactSimpleDTO> contacts, List<AddressSimpleDTO> addresses) {
    this(
        user.getId(),
        NormalizeOutput.name(user.getName()),
        NormalizeOutput.cpf(user.getCpf()),
        user.getEmail(),
        user.getRole(),
        contacts,
        addresses
    );
  }
}
