package com.dad.sales_api.shared.mappers;

import com.dad.sales_api.shared.helpers.NormalizeOutput;
import com.dad.sales_api.shared.persistence.postgres.dto.AddressSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.AddressEntity;

public class AddressMapper {
  public static AddressSimpleDTO convertEntityToSimpleDTO(AddressEntity entity){
    return new AddressSimpleDTO(
        entity.getId(),
        NormalizeOutput.cep(entity.getCep()),
        NormalizeOutput.addressInfos(entity.getState()),
        NormalizeOutput.addressInfos(entity.getCity()),
        NormalizeOutput.addressInfos(entity.getNeighborhood()),
        entity.getNumber(),
        entity.getComplement()
    );
  }
}
