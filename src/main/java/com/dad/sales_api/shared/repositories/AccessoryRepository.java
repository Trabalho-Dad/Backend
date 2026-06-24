package com.dad.sales_api.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.entities.AccessoryEntity;

public interface AccessoryRepository extends JpaRepository<AccessoryEntity, Integer>{
  
}
