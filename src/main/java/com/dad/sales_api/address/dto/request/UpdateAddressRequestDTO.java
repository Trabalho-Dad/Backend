package com.dad.sales_api.address.dto.request;

import com.dad.sales_api.shared.helpers.RegexPatterns;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.dad.sales_api.shared.SalesConstants.*;

public record UpdateAddressRequestDTO (
    @Pattern(
        regexp = RegexPatterns.CEP,
        message = "{validation.cep.regex}"
    )
    String cep,

    @Size(
        min = MIN_STATE_LENGTH,
        max = MAX_STATE_LENGTH,
        message = "{validation.state.size}"
    )
    String state,

    @Size(
        min = MIN_CITY_LENGTH,
        max = MAX_CITY_LENGTH,
        message = "{validation.city.size}"
    )
    String city,

    @Size(
        min = MIN_NEIGHBORHOOD_LENGTH,
        max = MAX_NEIGHBORHOOD_LENGTH,
        message = "{validation.neighborhood.size}"
    )
    String neighborhood,

    @Size(
        min = MIN_STREET_LENGTH,
        max = MAX_STREET_LENGTH,
        message = "{validation.street.size}"
    )
    String street,

    @Size(
        min = MIN_NUMBER_LENGTH,
        max = MAX_NUMBER_LENGTH,
        message = "{validation.number.size}"
    )
    String number,

    @Size(
        max = MAX_COMPLEMENT_LENGTH,
        message = "{validation.complement.size}"
    )
    String complement
){
}
