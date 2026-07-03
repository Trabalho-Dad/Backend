package com.dad.sales_api.shared.persistence.postgres.entities;

import com.dad.sales_api.shared.SalesConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contact")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = SalesConstants.MAX_CONTACT_VALUE_LENGTH)
  private String value;

  @ManyToOne
  @JoinColumn(name = "id_contact_type", nullable = false)
  private ContactTypeEntity contactType;

  @ManyToOne
  @JoinColumn(name = "id_user", nullable = false)
  private UserEntity user;
}
