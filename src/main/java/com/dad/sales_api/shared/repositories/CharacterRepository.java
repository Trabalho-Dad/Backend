package com.dad.sales_api.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dad.sales_api.shared.entities.CharacterEntity;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Integer>, JpaSpecificationExecutor<CharacterEntity> {
  
}
