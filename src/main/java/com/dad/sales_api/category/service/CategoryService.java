package com.dad.sales_api.category.service;

import com.dad.sales_api.category.dto.output.FindAllCategoriesOutputDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.CategoryEntity;
import com.dad.sales_api.shared.persistence.postgres.repositories.CategoryRepository;
import com.dad.sales_api.shared.persistence.postgres.specifications.CategorySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("publicCategoryService")
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public List<FindAllCategoriesOutputDTO> findAll(){
    Specification<CategoryEntity> spec = Specification
        .where(CategorySpecification.withStatus(true));

    return categoryRepository.findAll(spec).stream().map(c ->  new FindAllCategoriesOutputDTO(
          c.getId(),
          c.getName(),
          c.getDescription()
      )
    ).toList();
  }
}
