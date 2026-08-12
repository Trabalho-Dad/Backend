package com.dad.sales_api.shared.persistence.postgres.repositories;

import com.dad.sales_api.shared.enums.PaymentStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dad.sales_api.shared.persistence.postgres.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer>, JpaSpecificationExecutor<PaymentEntity> {
  PaymentEntity findFirstByUserOrderId(Integer userOrderId);
  Optional<PaymentEntity> findByIdAndUserOrder_User_Id(
      Integer id,
      Integer userId
  );

  @Query("SELECT SUM(p.payValue) FROM PaymentEntity p WHERE p.paymentStatus = :status AND p.payDate > :since")
  Optional<BigDecimal> sumPayValueByPaymentStatusAndPayDateAfter(
      @Param("status") PaymentStatusEnum status,
      @Param("since") LocalDate since
  );
}
