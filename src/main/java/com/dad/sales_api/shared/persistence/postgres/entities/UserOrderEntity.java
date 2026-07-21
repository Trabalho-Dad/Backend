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

  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private OrderStatusEnum status;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "id_user", nullable = false)
  private UserEntity user;

  @OneToMany(mappedBy = "userOrder")
  private List<UserOrderFigureEntity> figures;

  @OneToMany(mappedBy = "userOrder")
  private List<PaymentEntity> payments;

  public UserOrderEntity(OrderStatusEnum status, UserEntity user) {
    this.status = status;
    this.user = user;
  }
}