package com.dad.sales_api.shared.persistence.postgres.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.dad.sales_api.shared.SalesConstants;
import jakarta.persistence.*;
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

  @Column(nullable = false, unique = true, length = SalesConstants.MAX_CODE_LENGTH)
  private String code;

  @Column(name = "discount_pct", precision = 5, scale = 2)
  private BigDecimal discountPct;

  @Column(name = "usage_limit")
  private Integer usageLimit;

  @Column(name = "usage_count")
  private Integer usageCount = 0;
  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;


  @ManyToMany(mappedBy = "coupons")
  private List<UserOrderEntity> userOrders;

  public CouponEntity(String code, BigDecimal discountPct, Integer usageLimit, Integer usageCount, LocalDate startDate, LocalDate endDate) {
    this.code = code;
    this.discountPct = discountPct;
    this.usageLimit = usageLimit;
    this.usageCount = usageCount;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Boolean getActive(){
    return this.getUsageLimit() > this.getUsageCount() || this.getStartDate().isBefore(LocalDate.now());
  }
}