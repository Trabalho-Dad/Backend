package com.dad.sales_api.shared.persistence.mongo.documents;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.dad.sales_api.shared.enums.RoleEnum;

import java.time.LocalDateTime;

@Document(collection = "password_reset_codes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetCode {
  @Id
  private String id;
  private String email;
  private RoleEnum role;
  private Integer code;
  private Boolean used;

  @Indexed
  private LocalDateTime expiresAt;

  public PasswordResetCode(String email, RoleEnum role, Integer code, LocalDateTime expiresAt) {
    this.email = email;
    this.role = role;
    this.code = code;
    this.expiresAt = expiresAt;
    this.used = false;
  }

  @Override
  public String toString() {
    return "PasswordResetCode{" +
        "id='" + id + '\'' +
        ", email='" + email + '\'' +
        ", role=" + role +
        ", code=" + code +
        ", used=" + used +
        ", expiresAt=" + expiresAt +
        '}';
  }
}
