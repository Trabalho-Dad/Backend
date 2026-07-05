package com.dad.sales_api.shared.mappers;

import com.dad.sales_api.shared.persistence.postgres.dto.AddressSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.AddressEntity;

public class AddressMapper {
  public static AddressSimpleDTO convertToSimpleDTO(AddressEntity entity){
    return new AddressSimpleDTO(
        entity.getId(),
        entity.getCep(),
        entity.getCountry(),
        entity.getState(),
        entity.getCity(),
        entity.getNeighborhood(),
        entity.getNumber(),
        entity.getComplement()
    );
  }
}
