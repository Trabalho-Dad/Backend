package com.dad.sales_api.shared.persistence.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dad.sales_api.shared.persistence.postgres.entities.CharacterEntity;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Integer>, JpaSpecificationExecutor<CharacterEntity> {
  
}
