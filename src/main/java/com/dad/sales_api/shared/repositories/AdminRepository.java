package com.dad.sales_api.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.entities.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer>{
  AdminEntity findByEmail(String email);
}
