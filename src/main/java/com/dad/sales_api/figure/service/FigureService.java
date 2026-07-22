package com.dad.sales_api.figure.service;

import java.util.List;
import java.util.Optional;

import com.dad.sales_api.shared.enums.ImageTypeEnum;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dad.sales_api.figure.dto.input.FindManyFiguresInputDTO;
import com.dad.sales_api.figure.dto.output.FindFigureByIdOutputDTO;
import com.dad.sales_api.figure.dto.output.FindManyFiguresOutputDTO;
import com.dad.sales_api.shared.persistence.postgres.dto.FigureSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.FigureEntity;
import com.dad.sales_api.shared.exceptions.NotFoundException;
import com.dad.sales_api.shared.persistence.postgres.repositories.FigureRepository;
import com.dad.sales_api.shared.persistence.postgres.specifications.FigureSpecification;
import com.dad.sales_api.shared.mappers.AccessoryMapper;
import com.dad.sales_api.shared.mappers.CategoryMapper;
import com.dad.sales_api.shared.mappers.CharacterMapper;
import com.dad.sales_api.shared.mappers.FigureMapper;
import com.dad.sales_api.shared.mappers.ImageMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FigureService {
  private final FigureRepository figureRepository;

  @Transactional
  public FindManyFiguresOutputDTO findMany(FindManyFiguresInputDTO input){
    Specification<FigureEntity> spec = Specification
      .where(FigureSpecification.withName(input.name()))
      .and(FigureSpecification.withStatus(input.active()))
      .and(FigureSpecification.withCategory(input.categoryId()));

    int count = (int) figureRepository.count(spec);

    int totalPages = (int) Math.ceil((double) count / input.take());

    List<FigureEntity> figures = figureRepository.findAll(
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

    List<FigureSimpleDTO> output = figures.stream()
      .map(FigureMapper::convertEntityToSimpleDTO)
      .map(f -> new FigureSimpleDTO(
        f.id(),
        f.name(),
        f.description(),
        f.price(),
        f.quantity(),
        f.active(),
        f.isLaunch(),
        f.category(),
        f.images().stream()
          .filter(image -> image.imageType() == ImageTypeEnum.PRIMARY_FIGURE)
          .toList()
      ))
      .toList();

    return new FindManyFiguresOutputDTO(output, totalPages, count);
  }

  @Transactional
  public FindFigureByIdOutputDTO findById(Integer id){
    Optional<FigureEntity> figure = figureRepository.findById(id);

    FigureEntity entity = figure.orElseThrow(
      () -> new NotFoundException("Boneco não encontrado!")
    );

    return new FindFigureByIdOutputDTO(
      entity.getId(),
      entity.getName(),
      entity.getDescription(),
      entity.getPrice(),
      entity.getQuantity(),
      entity.getActive(),
      entity.getIsLaunch(),
      CharacterMapper.convertToSimpleDTO(entity.getCharacter()),
      entity.getAccessories()
        .stream()
        .map(AccessoryMapper::convertToSimpleDTO)
        .toList(),
      entity.getCategories()
        .stream()
        .map(CategoryMapper::convertEntityToSimpleDTO)
        .toList(),
      entity.getImages()
        .stream()
        .map(ImageMapper::convertEntityToSimpleDTO)
        .toList()
    );
  }
}
