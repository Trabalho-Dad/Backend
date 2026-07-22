package com.dad.sales_api.address.dto.output;

import com.dad.sales_api.shared.persistence.postgres.dto.AddressSimpleDTO;

import java.util.List;

public record FindMyAddressesOutputDTO(
    List<AddressSimpleDTO> addresses,
    Integer quantity,
    Integer limit
) {
}
