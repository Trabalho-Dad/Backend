package com.dad.sales_api.admin.image.service;

import com.dad.sales_api.admin.image.dto.input.FindManyImagesInputDTO;
import com.dad.sales_api.admin.image.dto.output.FindManyImagesOutputDTO;
import com.dad.sales_api.shared.dto.ImageSimpleDTO;
import com.dad.sales_api.shared.persistence.postgres.specifications.ImageSpecification;
import com.dad.sales_api.shared.mappers.ImageMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dad.sales_api.admin.image.dto.input.CreateImageInputDTO;
import com.dad.sales_api.admin.image.dto.output.CreateImageOutputDTO;
import com.dad.sales_api.shared.persistence.postgres.entities.ImageEntity;
import com.dad.sales_api.shared.persistence.postgres.repositories.ImageRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service("adminImageService")
@RequiredArgsConstructor
public class ImageService {
  private final ImageRepository imageRepository;

  public CreateImageOutputDTO create(CreateImageInputDTO input) {
    ImageEntity image = new ImageEntity(
        input.description(),
        input.url(),
        input.imageType()
    );

    imageRepository.save(image);

    return new CreateImageOutputDTO(
        image.getId(),
        image.getDescription(),
        image.getUrl(),
        image.getImageType()
    );
  }

  public FindManyImagesOutputDTO findMany(
      FindManyImagesInputDTO input
  ){
    Specification<ImageEntity> spec = Specification
        .where(ImageSpecification.containingDescription(input.description()))
        .and(ImageSpecification.withType(input.type()));

    int count = (int) imageRepository.count(spec);

    int totalPages = (int) Math.ceil((double) count / input.take());

    List<ImageEntity> images = imageRepository.findAll(
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

    List<ImageSimpleDTO> output = images.stream()
        .map(ImageMapper::convertEntityToSimpleDTO)
        .toList();

    return new FindManyImagesOutputDTO(output, totalPages, count);
  }
}
