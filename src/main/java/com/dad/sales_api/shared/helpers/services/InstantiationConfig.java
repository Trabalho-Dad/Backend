package com.dad.sales_api.shared.helpers.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.Random;

@Configuration
public class InstantiationConfig {
  @Bean
  public Random random() {
    return new Random();
  }

  @Bean
  public RestClient.Builder restClientBuilder() {
    return RestClient.builder();
  }
}
