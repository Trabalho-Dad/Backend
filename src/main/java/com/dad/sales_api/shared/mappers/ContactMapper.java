package com.dad.sales_api.shared.mappers;

import com.dad.sales_api.shared.persistence.postgres.dto.ContactSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.ContactEntity;

public class ContactMapper {
  public static ContactSimpleDTO convertEntityToSimpleDTO(ContactEntity entity){
    return new ContactSimpleDTO(
        entity.getId(),
        entity.getValue(),
        entity.getContactType().getType()
    );
  }
}
