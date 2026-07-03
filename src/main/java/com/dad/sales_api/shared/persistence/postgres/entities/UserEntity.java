package com.dad.sales_api.shared.persistence.postgres.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.dad.sales_api.shared.SalesConstants;
import com.dad.sales_api.shared.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, unique = true, length = SalesConstants.CPF_LENGTH)
  private String cpf;

  @Column(nullable = false, length = SalesConstants.MAX_NAME_LENGTH)
  private String name;

  @Column(nullable = false, unique = true, length = SalesConstants.MAX_EMAIL_LENGTH)
  private String email;

  @Column(nullable = false, length = SalesConstants.MAX_HASH_LENGTH)
  private String password;

  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false)
  private RoleEnum role;
  
  @Column(name = "creation_at", nullable = false, updatable = false, insertable = false)
  private LocalDateTime creationAt;

  @Column(name = "deactivated_at")
  private LocalDateTime deactivatedAt;

  @OneToMany(mappedBy = "user")
  private List<ContactEntity> contacts;

  @OneToMany(mappedBy = "user")
  private List<AddressEntity> addresses;

  @OneToMany(mappedBy = "user")
  private List<UserOrderEntity> orders;
}