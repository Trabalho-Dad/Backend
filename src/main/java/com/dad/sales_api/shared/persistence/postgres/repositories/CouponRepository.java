package com.dad.sales_api.shared.persistence.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.persistence.postgres.entities.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, Integer>{
  
}
