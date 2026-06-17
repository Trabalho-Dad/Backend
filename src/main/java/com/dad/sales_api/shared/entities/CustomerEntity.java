package com.dad.sales_api.shared.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 150)
  private String name;

  @Column(nullable = false, unique = true, length = 255)
  private String email;

  @Column(nullable = false, length = 255)
  private String password;
  
  @Column(name = "creation_at", nullable = false, updatable = false, insertable = false)
  private LocalDateTime creationAt;

  @Column(name = "deactivated_at")
  private LocalDateTime deactivatedAt;

  @OneToMany(mappedBy = "customer")
  private List<ContactEntity> contacts;

  @OneToMany(mappedBy = "customer")
  private List<AddressEntity> addresses;

  @OneToMany(mappedBy = "customer")
  private List<CustomerOrderEntity> orders;
}