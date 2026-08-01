package com.dad.sales_api.shared.persistence.postgres.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.dad.sales_api.shared.enums.OrderStatusEnum;
import com.dad.sales_api.shared.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserOrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(precision = 10, scale = 2)
  private BigDecimal price;

  @Column(name = "final_price", precision = 10, scale = 2)
  private BigDecimal finalPrice;

  @Column(precision = 10, scale = 2)
  private BigDecimal discount;

  @Column(precision = 10, scale = 2, name = "shipping_cost")
  private BigDecimal shippingCost;

  @Column(name = "estimated_delivery_time")
  private Integer estimatedDeliveryTime;

  @Column(name = "installments_count")
  private Integer installmentsCount;

  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private OrderStatusEnum status;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "id_user", nullable = false)
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "id_address")
  private AddressEntity address;

  @OneToMany(mappedBy = "userOrder")
  private List<UserOrderFigureEntity> figures;

  @OneToMany(mappedBy = "userOrder")
  private List<PaymentEntity> payments;

  @ManyToMany
  @JoinTable(
      name = "user_order_coupons",
      joinColumns = @JoinColumn(name = "id_user_order"),
      inverseJoinColumns = @JoinColumn(name = "id_coupon")
  )
  private List<CouponEntity> coupons;

  public UserOrderEntity(OrderStatusEnum status, UserEntity user) {
    this.status = status;
    this.user = user;
  }

  public void increaseOrderPrice(BigDecimal value){
    this.finalPrice.add(value);
  }
}