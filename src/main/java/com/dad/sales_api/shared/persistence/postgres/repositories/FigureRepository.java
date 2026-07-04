package com.dad.sales_api.shared.persistence.postgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dad.sales_api.shared.persistence.postgres.entities.FigureEntity;

public interface FigureRepository extends JpaRepository<FigureEntity, Integer>, JpaSpecificationExecutor<FigureEntity>{
}
