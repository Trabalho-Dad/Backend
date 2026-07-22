package com.dad.sales_api.shared.enums;

public enum PaymentTypeEnum {

  PIX(1),
  CREDIT_CARD(2),
  DEBIT_CARD(3),
  CASH(4),
  BANK_TRANSFER(5),
  BOLETO(6);

  private final int code;

  PaymentTypeEnum(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public static PaymentTypeEnum fromCode(int code) {
    for (PaymentTypeEnum payment : values()) {
      if (payment.code == code) {
        return payment;
      }
    }
    throw new IllegalArgumentException("Código de pagamento inválido: " + code);
  }
}