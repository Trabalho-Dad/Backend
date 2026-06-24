package com.dad.sales_api.shared.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dad.sales_api.shared.utils.enums.RoleEnum;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {
  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration-ms}")
  private long expirationMs;

  private SecretKey key() {
    return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(String email, RoleEnum role, Integer id) {
    return Jwts.builder()
        .subject(email)
        .claim("role", role)
        .claim("id", id)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expirationMs))
        .signWith(key())
        .compact();
  }

  public String getEmailFromToken(String token) {
    return Jwts.parser()
      .verifyWith(key())
      .build()
      .parseSignedClaims(token)
      .getPayload()
      .getSubject();
  }

  public RoleEnum getRoleFromToken(String token) {
    String role = Jwts.parser()
      .verifyWith(key())
      .build()
      .parseSignedClaims(token)
      .getPayload()
      .get("role", String.class);

    return RoleEnum.valueOf(role);
  }

  public Integer getIdFromToken(String token) {
    String role = Jwts.parser()
      .verifyWith(key())
      .build()
      .parseSignedClaims(token)
      .getPayload()
      .get("id", String.class);

    return Integer.valueOf(role);
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().verifyWith(key()).build().parseSignedClaims(token);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }
}