package com.dad.sales_api.shared.persistence.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.persistence.postgres.entities.CouponEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CouponRepository extends JpaRepository<CouponEntity, Integer>, JpaSpecificationExecutor<CouponEntity> {
  
}
