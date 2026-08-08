package com.dad.sales_api.shared.helpers.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@Slf4j
public class EmailService {

  private final RestClient restClient;
  private final String senderEmail;
  private final String senderName;

  public EmailService(
      @Value("${brevo.api.key}") String apiKey,
      @Value("${brevo.sender.email}") String senderEmail,
      @Value("${brevo.sender.name}") String senderName
  ) {
    this.senderEmail = senderEmail;
    this.senderName = senderName;

    this.restClient = RestClient.builder()
        .baseUrl("https://api.brevo.com/v3/smtp/email")
        .defaultHeader("api-key", apiKey)
        .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  @Async
  public void send(String to, String subject, String html) {

    try {
      BrevoEmailRequest request = new BrevoEmailRequest(
          new Sender(senderName, senderEmail),
          List.of(new Recipient(to)),
          subject,
          html
      );

      restClient.post()
          .body(request)
          .retrieve()
          .toBodilessEntity();

      log.info("E-mail enviado com sucesso para {}", to);

    } catch (Exception e) {
      log.error("Erro ao enviar e-mail para {}", to, e);
    }
  }

  private record BrevoEmailRequest(
      Sender sender,
      List<Recipient> to,
      String subject,
      String htmlContent
  ) {}

  private record Sender(
      String name,
      String email
  ) {}

  private record Recipient(
      String email
  ) {}
}