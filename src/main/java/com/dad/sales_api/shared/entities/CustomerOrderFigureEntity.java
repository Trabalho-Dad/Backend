package com.dad.sales_api.shared.entities;

import java.math.BigDecimal;

import com.dad.sales_api.shared.entities.custom_id.CustomerOrderFigureId;

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
@Table(name = "customer_order_figure")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerOrderFigureEntity {
  @EmbeddedId
  private CustomerOrderFigureId id;

  @ManyToOne
  @MapsId("customerOrderId")
  @JoinColumn(name = "customer_order_id")
  private CustomerOrderEntity customerOrder;

  @ManyToOne
  @MapsId("figureId")
  @JoinColumn(name = "figure_id")
  private FigureEntity figure;

  @Column(nullable = false)
  private Integer quantity;

  @Column(precision = 10, scale = 2)
  private BigDecimal price;
}