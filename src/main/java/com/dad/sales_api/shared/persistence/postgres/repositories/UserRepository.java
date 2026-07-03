package com.dad.sales_api.shared.persistence.postgres.repositories;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.persistence.postgres.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
  UserEntity findByEmail(String email);

  UserEntity findByCpf(String cpf);
}