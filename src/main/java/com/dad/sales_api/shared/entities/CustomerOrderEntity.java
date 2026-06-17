package com.dad.sales_api.shared.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer_order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerOrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(precision = 10, scale = 2)
  private BigDecimal price;

  @Column(name = "final_price", precision = 10, scale = 2)
  private BigDecimal finalPrice;

  @Column(precision = 10, scale = 2)
  private BigDecimal discount;

  @Column(length = 50)
  private String status;

  @Column(name = "installments_count")
  private Integer installmentsCount;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "id_customer", nullable = false)
  private CustomerEntity customer;

  @OneToMany(mappedBy = "customerOrder")
  private List<CustomerOrderFigureEntity> items;

  @OneToMany(mappedBy = "customerOrder")
  private List<PaymentEntity> payments;
}