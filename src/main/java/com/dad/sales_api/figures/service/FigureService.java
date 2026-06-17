package com.dad.sales_api.figures.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.dad.sales_api.figures.dto.input.FindManyFiguresInputDTO;
import com.dad.sales_api.figures.dto.output.FindFigureByIdOutputDTO;
import com.dad.sales_api.figures.dto.output.FindManyFiguresOutputDTO;
import com.dad.sales_api.shared.dto.FigureSimpleDTO;
import com.dad.sales_api.shared.entities.FigureEntity;
import com.dad.sales_api.shared.exceptions.NotFoundException;
import com.dad.sales_api.shared.mappers.AccessoryMapper;
import com.dad.sales_api.shared.mappers.CategoryMapper;
import com.dad.sales_api.shared.mappers.CharacterMapper;
import com.dad.sales_api.shared.repositories.FigureRepository;
import com.dad.sales_api.shared.specifications.FigureSpecification;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FigureService {
  private final FigureRepository figureRepository;

  public FindManyFiguresOutputDTO findMany(FindManyFiguresInputDTO input){
    Specification<FigureEntity> spec = Specification
      .where(FigureSpecification.withName(input.name()))
      .and(FigureSpecification.withStatus(input.active()));

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
      .map(this::toDTO)
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
      entity.getImgUrl(),
      entity.getQuantity(),
      entity.getActive(),
      CharacterMapper.convertToSimpleDTO(entity.getCharacter()),
      entity.getAccessories()
        .stream()
        .map(AccessoryMapper::convertToSimpleDTO)
        .toList(),
      entity.getCategories()
        .stream()
        .map(CategoryMapper::convertEntityToSimpleDTO)
        .toList()
    );
  }

  private FigureSimpleDTO toDTO(FigureEntity figure) {
    return new FigureSimpleDTO(
      figure.getId(),
      figure.getName(),
      figure.getDescription(),
      figure.getPrice(),
      figure.getImgUrl(),
      figure.getQuantity(),
      figure.getActive()
    );
  }
}
