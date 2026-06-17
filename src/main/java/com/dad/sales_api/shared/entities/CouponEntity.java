package com.dad.sales_api.shared.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coupon")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CouponEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, unique = true, length = 50)
  private String code;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(name = "discount_pct", precision = 5, scale = 2)
  private BigDecimal discountPct;

  @Column(name = "usage_limit")
  private Integer usageLimit;

  @Column(name = "usage_count")
  private Integer usageCount = 0;

  @Column(nullable = false)
  private Boolean active = true;

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;
}