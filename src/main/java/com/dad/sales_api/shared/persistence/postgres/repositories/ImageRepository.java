package com.dad.sales_api.shared.persistence.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.persistence.postgres.entities.ImageEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer>, JpaSpecificationExecutor<ImageEntity> {
  
}
