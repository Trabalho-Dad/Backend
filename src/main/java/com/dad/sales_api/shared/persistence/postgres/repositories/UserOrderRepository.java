package com.dad.sales_api.shared.persistence.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.persistence.postgres.entities.UserOrderEntity;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Integer> {
  
}
