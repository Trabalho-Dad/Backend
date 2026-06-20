package com.dad.sales_api.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dad.sales_api.shared.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>, JpaSpecificationExecutor<CategoryEntity>{
  
}
