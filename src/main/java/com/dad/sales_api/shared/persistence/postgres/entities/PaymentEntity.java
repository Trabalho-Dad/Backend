package com.dad.sales_api.shared.persistence.postgres.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.dad.sales_api.shared.enums.PaymentStatusEnum;
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

  @Column(name = "installment_number", nullable = false)
  private Integer installmentNumber;

  @Column(name = "pay_value", nullable = false, precision = 10, scale = 2)
  private BigDecimal payValue;

  @Column(name = "created_at", insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "pay_date")
  private LocalDate payDate;

  @Column(name = "due_date",nullable = false)
  private LocalDate dueDate;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "payment_type", nullable = false)
  private PaymentTypeEnum paymentType;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "payment_status", nullable = false)
  private PaymentStatusEnum paymentStatus;

  @ManyToOne
  @JoinColumn(name = "id_user_order", nullable = false)
  private UserOrderEntity userOrder;

  public PaymentEntity(Integer installmentNumber, BigDecimal payValue, LocalDate dueDate, PaymentTypeEnum paymentType, PaymentStatusEnum paymentStatus, UserOrderEntity userOrder) {
    this.installmentNumber = installmentNumber;
    this.payValue = payValue;
    this.dueDate = dueDate;
    this.paymentType = paymentType;
    this.paymentStatus = paymentStatus;
    this.userOrder = userOrder;
  }
}