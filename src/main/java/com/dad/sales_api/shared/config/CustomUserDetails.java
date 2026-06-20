package com.dad.sales_api.shared.config;

import java.util.List;
import org.springframework.security.core.userdetails.User;

import com.dad.sales_api.shared.utils.enums.RoleEnum;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;

@Getter
public class CustomUserDetails extends User {
  private final Integer id;
  private final RoleEnum role;

  public CustomUserDetails(
    Integer id,
    String username,
    String password,
    RoleEnum role
  ) {
    super(
      username,
      password,
      List.of(new SimpleGrantedAuthority("ROLE_" + role.name()))
    );

    this.id = id;
    this.role = role;
  }
}