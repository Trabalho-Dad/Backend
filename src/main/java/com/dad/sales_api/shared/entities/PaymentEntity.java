package com.dad.sales_api.shared.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "pay_value", nullable = false, precision = 10, scale = 2)
  private BigDecimal payValue;

  @Column(nullable = false)
  private Integer installmentNumber;

  private LocalDate payDate;

  @Column(nullable = false)
  private LocalDate validDate;

  @ManyToOne
  @JoinColumn(name = "id_payment_type", nullable = false)
  private PaymentTypeEntity paymentType;

  @ManyToOne
  @JoinColumn(name = "id_customer_order", nullable = false)
  private CustomerOrderEntity customerOrder;
}