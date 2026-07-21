package com.dad.sales_api.shared.helpers;

import com.dad.sales_api.shared.exceptions.BadRequestException;

import java.util.Set;

public class NormalizeInput {
  public static String cpf(String cpf){
    if (cpf == null || cpf.isBlank()) return cpf;

    return removeBlank(cpf.replaceAll("[^\\d]", ""));
  }

  public static String email(String email){
    if (email == null || email.isBlank()) return email;

    return removeBlank(email.toLowerCase());
  }

  public static String name(String name){
    if (name == null || name.isBlank()) return name;

    return removeBlank(name.toLowerCase());
  }

  public static String description(String description){
    if (description == null || description.isBlank()) return "";

    return removeBlank(description);
  }

  public static String password(String password){
    if (password == null || password.isBlank()) return password;

    return removeBlank(password);
  }

  public static String url(String url){
    if (url == null || url.isBlank()) return url;

    return removeBlank(url);
  }

  public static String cep(String cep){
    if (cep == null || cep.isEmpty()) return cep;

    return removeBlank(cep.replaceAll("[^\\d]", ""));
  }

  public static String addressInfos(String info){
    if (info == null || info.isBlank()) return info;

    return removeBlank(info.toLowerCase());
  }

  public static String removeBlank(String input) {
    if (input == null || input.isBlank()) return input;

    return input.trim();
  }
}
