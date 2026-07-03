package com.dad.sales_api.shared.persistence.postgres.entities;

import java.math.BigDecimal;

import com.dad.sales_api.shared.persistence.postgres.entities.custom_id.UserOrderFigureId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "user_order_figure")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserOrderFigureEntity {
  @EmbeddedId
  private UserOrderFigureId id;

  @ManyToOne
  @MapsId("idUserOrder")
  @JoinColumn(name = "id_user_order")
  private UserOrderEntity userOrder;

  @ManyToOne
  @MapsId("idFigure")
  @JoinColumn(name = "id_figure")
  private FigureEntity figure;

  @Column(nullable = false)
  private Integer quantity;

  @Column(precision = 10, scale = 2)
  private BigDecimal price;
}