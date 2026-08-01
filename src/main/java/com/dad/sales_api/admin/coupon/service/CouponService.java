package com.dad.sales_api.admin.coupon.service;

import com.dad.sales_api.admin.coupon.dto.input.CreateCouponInputDTO;
import com.dad.sales_api.admin.coupon.dto.input.FindCouponByIdInputDTO;
import com.dad.sales_api.admin.coupon.dto.input.FindManyCouponsInputDTO;
import com.dad.sales_api.admin.coupon.dto.output.CreateCouponOutputDTO;
import com.dad.sales_api.admin.coupon.dto.output.FindCouponByIdOutputDTO;
import com.dad.sales_api.admin.coupon.dto.output.FindManyCouponsOutputDTO;
import com.dad.sales_api.shared.exceptions.ConflictException;
import com.dad.sales_api.shared.exceptions.NotFoundException;
import com.dad.sales_api.shared.helpers.services.MessageService;
import com.dad.sales_api.shared.mappers.CouponMapper;
import com.dad.sales_api.shared.persistence.postgres.dto.CouponSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.CouponEntity;
import com.dad.sales_api.shared.persistence.postgres.repositories.CouponRepository;
import com.dad.sales_api.shared.persistence.postgres.specifications.CouponSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("adminCouponService")
@RequiredArgsConstructor
public class CouponService {
  private final CouponRepository couponRepository;

  private final MessageService messageService;

  @Transactional()
  public FindManyCouponsOutputDTO findMany(FindManyCouponsInputDTO input) {
    Specification<CouponEntity> spec = Specification
        .where(CouponSpecification.withCode(input.code()));

    if (Boolean.TRUE.equals(input.active())) {
      spec = spec
          .and(CouponSpecification.available(true))
          .and(CouponSpecification.validDate(LocalDate.now()));
    } else if (Boolean.FALSE.equals(input.active())) {
      spec = spec
          .and(
              CouponSpecification.invalidDate(LocalDate.now())
                  .or(CouponSpecification.available(false))
          );
    }

    int count = (int) couponRepository.count(spec);

    int totalPages = (int) Math.ceil((double) count / input.take());

    List<CouponSimpleDTO> coupons = couponRepository.findAll(
            spec,
            PageRequest.of(
                (input.page() <= totalPages ? input.page() : 1) - 1,
                input.take()
            )
        )
        .getContent()
        .stream()
        .map(CouponMapper::convertEntityToSimpleDTO)
        .toList();

    return new FindManyCouponsOutputDTO(
        coupons,
        totalPages,
        count
    );
  }

  public FindCouponByIdOutputDTO findById(
      FindCouponByIdInputDTO input
  ){
    CouponEntity coupon = find(input.id());

    return new FindCouponByIdOutputDTO(
        coupon.getId(),
        coupon.getCode(),
        coupon.getDiscountPct(),
        coupon.getUsageLimit(),
        coupon.getUsageCount(),
        coupon.getActive(),
        coupon.getStartDate(),
        coupon.getEndDate()
    );
  }

  public CreateCouponOutputDTO createCoupon(CreateCouponInputDTO input){
    if (input.endDate().isBefore(input.endDate())) throw new ConflictException(
        messageService.getMessage("validation.end-date.conflict")
    );

    CouponEntity coupon = new CouponEntity(
        input.code(),
        input.discountPct(),
        input.usageLimit(),
        0,
        input.startDate(),
        input.endDate()
    );

    couponRepository.save(coupon);

    return new CreateCouponOutputDTO(
        coupon.getId(),
        coupon.getCode(),
        coupon.getDiscountPct(),
        coupon.getUsageLimit(),
        coupon.getUsageCount(),
        coupon.getActive(),
        coupon.getStartDate(),
        coupon.getEndDate()
    );
  }

  public CouponEntity find(Integer id){
    return couponRepository.findById(id).orElseThrow(
        () -> new NotFoundException(
            messageService.getMessage("exception.coupon.not-found")
        )
    );
  }
}
