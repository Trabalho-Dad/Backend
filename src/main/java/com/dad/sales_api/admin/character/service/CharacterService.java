package com.dad.sales_api.admin.character.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dad.sales_api.admin.character.dto.input.CreateCharacterInputDTO;
import com.dad.sales_api.admin.character.dto.input.FindManyCharactersInputDTO;
import com.dad.sales_api.admin.character.dto.input.UpdateCharacterInputDTO;
import com.dad.sales_api.admin.character.dto.output.CreateCharacterOutputDTO;
import com.dad.sales_api.admin.character.dto.output.FindCharacterByIdOutputDTO;
import com.dad.sales_api.admin.character.dto.output.FindManyCharacterOutputDTO;
import com.dad.sales_api.admin.character.dto.output.UpdateCharacterOutputDTO;
import com.dad.sales_api.shared.dto.CharacterSimpleDTO;
import com.dad.sales_api.shared.entities.CharacterEntity;
import com.dad.sales_api.shared.entities.FigureEntity;
import com.dad.sales_api.shared.entities.ImageEntity;
import com.dad.sales_api.shared.exceptions.NotFoundException;
import com.dad.sales_api.shared.repositories.CharacterRepository;
import com.dad.sales_api.shared.repositories.ImageRepository;
import com.dad.sales_api.shared.specifications.CharacterSpecification;
import com.dad.sales_api.shared.utils.mappers.CharacterMapper;
import com.dad.sales_api.shared.utils.mappers.FigureMapper;
import com.dad.sales_api.shared.utils.mappers.ImageMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterService {
  private final CharacterRepository characterRepository;
  private final ImageRepository imageRepository;

  public FindManyCharacterOutputDTO findMany(
    FindManyCharactersInputDTO input
  ){
    Specification<CharacterEntity> spec = Specification
      .where(CharacterSpecification.withName(input.name()))
      .and(CharacterSpecification.withStatus(input.active()));

    int count = (int) characterRepository.count(spec);

    int totalPages = (int) Math.ceil((double) count / input.take());

    List<CharacterEntity> characters = characterRepository.findAll(
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

    List<CharacterSimpleDTO> output = characters.stream().map(CharacterMapper::convertToSimpleDTO).toList();

    return new FindManyCharacterOutputDTO(
      output,
      totalPages,
      count
    );
  }

  @Transactional
  public FindCharacterByIdOutputDTO findById(Integer id){
    CharacterEntity character = find(id);

    return new FindCharacterByIdOutputDTO(
      character.getId(),
      character.getName(),
      character.getDescription(),
      character.getActive(),
      character.getFigures().stream().map(FigureMapper::convertEntityToSimpleDTO).toList(),
      character.getImages().stream().map(ImageMapper::convertEntityToSimpleDTO).toList()
    );
  }

  public UpdateCharacterOutputDTO update(UpdateCharacterInputDTO input){
    CharacterEntity entity = find(input.id());

    if (input.name() != null && !input.name().equals(entity.getName())){
      entity.setName(input.name());
    }
    if (input.description() != null && !input.description().equals(entity.getDescription())){
      entity.setDescription(input.description());
    }
    if (input.active() != null && !input.active().equals(entity.getActive())){
      entity.setActive(input.active());
    }

    characterRepository.save(entity);

    return new UpdateCharacterOutputDTO(
      entity.getId(),
      entity.getName(),
      entity.getDescription(),
      entity.getActive()
    );
  }

  @Transactional
  public UpdateCharacterOutputDTO updateStatus(Integer id){
    CharacterEntity entity = find(id);
    Boolean active = entity.getActive();

    List<FigureEntity> figures = entity.getFigures() != null ? entity.getFigures() : new ArrayList<>();

    List<FigureEntity> updated = figures.stream()
      .map(i -> {
        if (i.getActive() == active) {
          if (i.getCategories().stream().anyMatch(c -> c.getActive())){
            i.setActive(!active);
          }
        }

        return i;
      })
      .collect(Collectors.toList());

    entity.setActive(!active);
    entity.setFigures(updated);

    characterRepository.save(entity);

    return new UpdateCharacterOutputDTO(
      entity.getId(),
      entity.getName(),
      entity.getDescription(),
      entity.getActive()
    );
  }

  public CreateCharacterOutputDTO create(CreateCharacterInputDTO input){
    List<ImageEntity> images = imageRepository.findAllById(input.imageIds());
    if (images.size() == 0) throw new NotFoundException("Nenhuma das imagens informadas foi encontrada");

    CharacterEntity entity = new CharacterEntity(
      input.name(),
      input.description(),
      input.active(),
      images
    );

    characterRepository.save(entity);

    return new CreateCharacterOutputDTO(
      entity.getId(),
      entity.getName(),
      entity.getDescription(),
      entity.getActive()
    );
  }

  private CharacterEntity find(Integer id){
    return characterRepository.findById(id).orElseThrow(
      () -> new NotFoundException("Personagem não foi encontrado.")
    );
  }
}
