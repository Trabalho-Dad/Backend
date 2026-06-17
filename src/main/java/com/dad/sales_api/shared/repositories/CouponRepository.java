package com.dad.sales_api.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.entities.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, Integer>{
  
}
