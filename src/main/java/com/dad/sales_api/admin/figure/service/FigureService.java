package com.dad.sales_api.admin.figure.service;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.dad.sales_api.admin.figure.dto.input.CreateFigureInputDTO;
import com.dad.sales_api.admin.figure.dto.input.FindManyFiguresInputDTO;
import com.dad.sales_api.admin.figure.dto.input.IncreaseOrDecreaseQuantityInputDTO;
import com.dad.sales_api.admin.figure.dto.output.CreateFigureOutputDTO;
import com.dad.sales_api.admin.figure.dto.output.FindFigureByIdOutputDTO;
import com.dad.sales_api.admin.figure.dto.output.FindManyFiguresOutputDTO;
import com.dad.sales_api.admin.figure.dto.output.IncreaseOrDecreaseQuantityOutputDTO;
import com.dad.sales_api.shared.dto.FigureSimpleDTO;
import com.dad.sales_api.shared.entities.AccessoryEntity;
import com.dad.sales_api.shared.entities.CategoryEntity;
import com.dad.sales_api.shared.entities.CharacterEntity;
import com.dad.sales_api.shared.entities.FigureEntity;
import com.dad.sales_api.shared.entities.ImageEntity;
import com.dad.sales_api.shared.exceptions.NotFoundException;
import com.dad.sales_api.shared.exceptions.BadRequestException;
import com.dad.sales_api.shared.repositories.AccessoryRepository;
import com.dad.sales_api.shared.repositories.CategoryRepository;
import com.dad.sales_api.shared.repositories.CharacterRepository;
import com.dad.sales_api.shared.repositories.FigureRepository;
import com.dad.sales_api.shared.repositories.ImageRepository;
import com.dad.sales_api.shared.specifications.CategorySpecification;
import com.dad.sales_api.shared.specifications.FigureSpecification;
import com.dad.sales_api.shared.utils.mappers.AccessoryMapper;
import com.dad.sales_api.shared.utils.mappers.CategoryMapper;
import com.dad.sales_api.shared.utils.mappers.CharacterMapper;
import com.dad.sales_api.shared.utils.mappers.FigureMapper;
import com.dad.sales_api.shared.utils.mappers.ImageMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service("adminFigureService")
@RequiredArgsConstructor
public class FigureService {
  private final FigureRepository figureRepository;
  private final CategoryRepository categoryRepository;
  private final CharacterRepository characterRepository;
  private final AccessoryRepository accessoryRepository;
  private final ImageRepository imageRepository;

  @Transactional
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
      .map(FigureMapper::convertEntityToSimpleDTO)
      .toList();

    return new FindManyFiguresOutputDTO(output, totalPages, count);
  }

  @Transactional
  public FindFigureByIdOutputDTO findById(Integer id){
    FigureEntity figure = find(id);

    return new FindFigureByIdOutputDTO(
      figure.getId(),
      figure.getName(),
      figure.getDescription(),
      figure.getPrice(),
      figure.getQuantity(),
      figure.getActive(),
      CharacterMapper.convertToSimpleDTO(figure.getCharacter()),
      figure.getAccessories()
        .stream()
        .map(AccessoryMapper::convertToSimpleDTO)
        .toList(),
      figure.getCategories()
        .stream()
        .map(CategoryMapper::convertEntityToSimpleDTO)
        .toList(),
      figure.getImages()
        .stream()
        .map(ImageMapper::convertEntityToSimpleDTO)
        .toList()
    );
  }

  public CreateFigureOutputDTO create(CreateFigureInputDTO input){
    CharacterEntity character = characterRepository.findById(input.characterId()).orElseThrow(
      () -> new NotFoundException("Personagem não encontrado!")
    );

    Specification<CategoryEntity> categorySpec = Specification
      .where(CategorySpecification.withIds(input.categoryIds()))
      .and(CategorySpecification.withStatus(Boolean.TRUE));

    List<CategoryEntity> categories = categoryRepository.findAll(categorySpec);
    
    if (categories.size() == 0) throw new NotFoundException("Nenhuma das categorias informadas foram encontradas");

    List<AccessoryEntity> accessories = accessoryRepository.findAllById(input.accessoryIds());
    if (accessories.size() == 0) throw new NotFoundException("Nenhum dos acessórios foram encontrados");

    List<ImageEntity> images = imageRepository.findAllById(input.accessoryIds());
    if (images.size() == 0) throw new NotFoundException("Nenhuma das imagens foram encontradas");

    FigureEntity figure = new FigureEntity(
      input.name(),
      input.description(),
      input.price(),
      input.quantity(),
      character.getActive() ? input.active() : Boolean.FALSE,
      character,
      accessories,
      categories,
      images
    );

    figureRepository.save(figure);

    return new CreateFigureOutputDTO(figure);
  }

  public IncreaseOrDecreaseQuantityOutputDTO increaseQuantity(IncreaseOrDecreaseQuantityInputDTO input){
    FigureEntity figure = find(input.id());

    Integer oldQuantity = figure.getQuantity();

    figure.setQuantity(oldQuantity + input.quantity());

    figureRepository.save(figure);

    return new IncreaseOrDecreaseQuantityOutputDTO(
      figure,
      oldQuantity
    );
  }

  public IncreaseOrDecreaseQuantityOutputDTO decreaseQuantity(IncreaseOrDecreaseQuantityInputDTO input){
    FigureEntity figure = find(input.id());

    Integer oldQuantity = figure.getQuantity();

    if (oldQuantity - input.quantity() < 0) throw new BadRequestException(
      String.format(
  "Estoque insuficiente. Quantidade disponível: %d. Quantidade solicitada: %d.",
          oldQuantity,
          input.quantity()
      )
    );

    figure.setQuantity(oldQuantity - input.quantity());

    figureRepository.save(figure);

    return new IncreaseOrDecreaseQuantityOutputDTO(
      figure,
      oldQuantity
    );
  }

  private FigureEntity find(Integer id){
    return figureRepository.findById(id).orElseThrow(
      () -> new NotFoundException("Boneco não encontrado")
    );
  }
}