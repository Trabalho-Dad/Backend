package com.dad.sales_api.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{
  CustomerEntity findByEmail(String email);  
}