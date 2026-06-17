package com.dad.sales_api.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.entities.CustomerOrderEntity;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Integer> {
  
}
