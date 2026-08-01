package com.dad.sales_api.shared.persistence.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.persistence.postgres.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer>, JpaSpecificationExecutor<PaymentEntity> {
  PaymentEntity findFirstByUserOrderId(Integer userOrderId);
  Optional<PaymentEntity> findByIdAndUserOrder_User_Id(
      Integer id,
      Integer userId
  );
}
