package com.dad.sales_api.admin.home.service;

import com.dad.sales_api.admin.home.dto.HomeKpisOutputDTO;
import com.dad.sales_api.shared.enums.PaymentStatusEnum;
import com.dad.sales_api.shared.persistence.postgres.repositories.FigureRepository;
import com.dad.sales_api.shared.persistence.postgres.repositories.PaymentRepository;
import com.dad.sales_api.shared.persistence.postgres.repositories.UserOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HomeService {
  private final UserOrderRepository orderRepository;
  private final PaymentRepository paymentRepository;
  private final FigureRepository figureRepository;

  public HomeKpisOutputDTO getKpis() {
    LocalDateTime last24h = LocalDateTime.now().minusDays(1);

    long ordersLast24h = orderRepository.countByCreatedAtAfter(last24h);

    BigDecimal totalReceivedLast24h = paymentRepository
        .sumPayValueByPaymentStatusAndPayDateAfter(PaymentStatusEnum.PAID, LocalDate.now().minusDays(1))
        .orElse(BigDecimal.ZERO);

    long totalActiveFigures = figureRepository.countByActive(Boolean.TRUE);

    return new HomeKpisOutputDTO(
        ordersLast24h,
        totalReceivedLast24h,
        totalActiveFigures
    );
  }
}