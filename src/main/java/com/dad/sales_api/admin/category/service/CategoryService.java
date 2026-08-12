package com.dad.sales_api.admin.category.service;

import java.util.List;

import com.dad.sales_api.shared.exceptions.ConflictException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.dad.sales_api.admin.category.dto.input.CreateCategoryInputDTO;
import com.dad.sales_api.admin.category.dto.input.FindManyCategoriesInputDTO;
import com.dad.sales_api.admin.category.dto.input.UpdateCategoryInputDTO;
import com.dad.sales_api.admin.category.dto.output.CreateCategoryOutputDTO;
import com.dad.sales_api.admin.category.dto.output.FindCategoryByIdOutputDTO;
import com.dad.sales_api.admin.category.dto.output.FindManyCategoriesOutputDTO;
import com.dad.sales_api.admin.category.dto.output.UpdateCategoryOutputDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.CategoryEntity;
import com.dad.sales_api.shared.persistence.postgres.entities.FigureEntity;
import com.dad.sales_api.shared.exceptions.NotFoundException;
import com.dad.sales_api.shared.persistence.postgres.repositories.CategoryRepository;
import com.dad.sales_api.shared.persistence.postgres.specifications.CategorySpecification;
import com.dad.sales_api.shared.mappers.CategoryMapper;
import com.dad.sales_api.shared.mappers.FigureMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service("adminCategoryService")
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public FindManyCategoriesOutputDTO findMany(
    FindManyCategoriesInputDTO input
  ){
    Specification<CategoryEntity> spec = Specification
      .where(CategorySpecification.withName(input.name()))
      .and(CategorySpecification.withStatus(input.active()));

    int count = (int) categoryRepository.count(spec);

    int totalPages = (int) Math.ceil((double) count / input.take());

    List<CategoryEntity> categories = categoryRepository.findAll(
      spec,
      PageRequest.of(
        (
          input.page() <= totalPages 
          ? input.page() 
          : 1
        ) - 1, 
        input.take()
      )
    ).getContent();

    return new FindManyCategoriesOutputDTO(
      categories.stream().map(CategoryMapper::convertEntityToSimpleDTO).toList(),
      totalPages,
      count
    );
  }

  @Transactional
  public FindCategoryByIdOutputDTO findById(Integer id){
    CategoryEntity category = find(id);

    return new FindCategoryByIdOutputDTO(
      category.getId(),
      category.getName(),
      category.getDescription(),
      category.getActive(),
      category.getFigures().stream().filter(f -> f.getActive()).map(FigureMapper::convertEntityToSimpleDTO).toList()
    );
  }

  public CreateCategoryOutputDTO create(CreateCategoryInputDTO input){
    CategoryEntity category = new CategoryEntity(
      input.name(),
      input.description(),
      input.active()
    );

    categoryRepository.save(category);

    return new CreateCategoryOutputDTO(category);
  }

  public UpdateCategoryOutputDTO update(UpdateCategoryInputDTO input){
    CategoryEntity category = find(input.id());

    if (input.name() != null && !input.name().equals(category.getName())){
      category.setName(input.name());
    }

    if (input.description() != null && !input.description().equals(category.getDescription())){
      category.setDescription(input.description());
    }

    if (input.active() != null && !input.active().equals(category.getActive())){
      boolean isDeactivating = Boolean.TRUE.equals(category.getActive()) && Boolean.FALSE.equals(input.active());

      if (isDeactivating) {
        for (FigureEntity figure : category.getFigures()) {
          if (figure.getCategories().size() == 1) {
            throw new ConflictException(
                "Não é possível desativar esta categoria. A figura '" + figure.getName() +
                    "' possui apenas esta categoria."
            );
          }
        }

        for (FigureEntity figure : category.getFigures()) {
          figure.getCategories().remove(category);
        }
        category.getFigures().clear();
      }

      category.setActive(input.active());
    }

    categoryRepository.save(category);

    return new UpdateCategoryOutputDTO(category);
  }

  @Transactional
  public UpdateCategoryOutputDTO updateStatus(Integer id){
    CategoryEntity category = find(id);

    boolean wasActive = Boolean.TRUE.equals(category.getActive());

    if (wasActive) {
      for (FigureEntity figure : category.getFigures()) {
        if (figure.getCategories().size() == 1) {
          throw new ConflictException(
              "Não é possível desativar esta categoria. A figura '" + figure.getName() +
                  "' possui apenas esta categoria."
          );
        }
      }

      for (FigureEntity figure : category.getFigures()) {
        figure.getCategories().remove(category);
      }
      category.getFigures().clear();
    }

    category.setActive(!wasActive);
    categoryRepository.save(category);

    return new UpdateCategoryOutputDTO(category);
  }

  private CategoryEntity find(Integer id){
    return categoryRepository.findById(id).orElseThrow(
      () -> new NotFoundException("Categoria não encontrada")
    );
  }
}
