package com.dad.sales_api.shared.persistence.postgres.repositories;

import com.dad.sales_api.shared.enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.persistence.postgres.entities.UserOrderEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Integer>, JpaSpecificationExecutor<UserOrderEntity> {
  Optional<UserOrderEntity> findByUserIdAndStatus(
      Integer userId,
      OrderStatusEnum status
  );
}
