package com.dad.sales_api.shared.persistence.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.persistence.postgres.entities.AddressEntity;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
  List<AddressEntity> findAllByUserId(Integer userId);

  Optional<AddressEntity> findByIdAndUserId(
      Integer id,
      Integer userId
  );
}
