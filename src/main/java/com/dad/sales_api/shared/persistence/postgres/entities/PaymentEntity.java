package com.dad.sales_api.shared.persistence.postgres.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.dad.sales_api.shared.enums.PaymentTypeEnum;
import jakarta.persistence.*;
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

  @Column(name = "pay_date")
  private LocalDate payDate;

  @Column(name = "due_date",nullable = false)
  private LocalDate dueDate;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "payment_type", nullable = false)
  private PaymentTypeEnum paymentType;

  @ManyToOne
  @JoinColumn(name = "id_user_order", nullable = false)
  private UserOrderEntity userOrder;
}