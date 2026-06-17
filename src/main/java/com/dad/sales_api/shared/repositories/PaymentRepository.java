package com.dad.sales_api.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.entities.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
  
}
