package com.dad.sales_api.shared.helpers.services;

import com.dad.sales_api.shared.helpers.dto.ValidatorOutputDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CpfValidatorService {

  private final RestClient restClient;

  @Value("${validator.token}")
  private String token;

  public CpfValidatorService(RestClient.Builder builder) {
    this.restClient = builder
        .baseUrl("https://api.invertexto.com/v1")
        .build();
  }

  public ValidatorOutputDTO validateCpf(String cpf) {
    return restClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/validator")
            .queryParam("value", cpf)
            .queryParam("type", "cpf")
            .queryParam("token", token)
            .build())
        .retrieve()
        .body(ValidatorOutputDTO.class);
  }
}