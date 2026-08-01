package com.dad.sales_api.shared.enums;

public enum OrderStatusEnum {
  IN_PROGRESS("Em progresso"),
  ORDERED("Pedido"),
  FINANCED("Pagamento em andamento"),
  PAID("Pago"),
  PROCESSING("Em processamento"),
  SHIPPED("Enviado"),
  DELIVERED("Entregue"),
  CANCELED("Cancelado");

  private final String description;

  OrderStatusEnum(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
