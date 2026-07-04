package com.dad.sales_api.shared.persistence.mongo.repositories;

import com.dad.sales_api.shared.persistence.mongo.documents.PasswordResetCode;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PasswordResetCodeRepository extends MongoRepository<PasswordResetCode, String> {
  public PasswordResetCode findByEmailAndUsed(String email, Boolean used);
  public PasswordResetCode findByEmail(String email);
}
