package com.dad.sales_api.shared.persistence.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.persistence.postgres.entities.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
  
}
