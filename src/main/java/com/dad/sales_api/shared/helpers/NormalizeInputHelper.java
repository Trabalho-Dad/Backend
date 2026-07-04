package com.dad.sales_api.shared.helpers;

public class NormalizeInputHelper {
  public static String normalizeCpf(String cpf){
    return cpf.replace("[^\\d]", "").trim();
  }
}
