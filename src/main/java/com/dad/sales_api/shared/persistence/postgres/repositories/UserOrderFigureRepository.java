package com.dad.sales_api.shared.persistence.postgres.repositories;

import com.dad.sales_api.shared.persistence.postgres.entities.UserOrderFigureEntity;
import com.dad.sales_api.shared.persistence.postgres.entities.custom_id.UserOrderFigureId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOrderFigureRepository extends JpaRepository<UserOrderFigureEntity, UserOrderFigureId> {
  Optional<UserOrderFigureEntity> findByUserOrderIdAndFigureId(Integer orderId, Integer figureId);
}
