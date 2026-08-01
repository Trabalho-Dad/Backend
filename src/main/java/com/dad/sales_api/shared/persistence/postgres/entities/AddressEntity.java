package com.dad.sales_api.shared.persistence.postgres.entities;

import com.dad.sales_api.shared.SalesConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = SalesConstants.MAX_COMPLEMENT_LENGTH)
  private String complement;

  @Column(length = SalesConstants.CEP_LENGTH)
  private String cep;

  @Column(length = SalesConstants.MAX_STATE_LENGTH)
  private String state;

  @Column(length = SalesConstants.MAX_CITY_LENGTH)
  private String city;

  @Column(length = SalesConstants.MAX_NEIGHBORHOOD_LENGTH)
  private String neighborhood;

  @Column(length = SalesConstants.MAX_STREET_LENGTH)
  private String street;

  @Column(length = SalesConstants.MAX_NUMBER_LENGTH)
  private String number;

  @ManyToOne
  @JoinColumn(name = "id_user", nullable = false)
  private UserEntity user;

  @OneToMany(mappedBy = "address")
  private List<UserOrderEntity> userOrders;
}