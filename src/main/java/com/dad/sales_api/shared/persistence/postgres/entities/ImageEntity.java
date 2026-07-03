package com.dad.sales_api.shared.persistence.postgres.entities;

import com.dad.sales_api.shared.enums.ImageTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "image")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false, length = 500)
  private String url;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "image_type", nullable = false)
  private ImageTypeEnum imageType;

  @OneToOne(mappedBy = "image")
  private AccessoryEntity accessory;

  @ManyToOne()
  @JoinColumn(name = "id_character", nullable = false)
  private CharacterEntity character;

  @ManyToOne()
  @JoinColumn(name = "id_figure", nullable = false)
  private FigureEntity figure;

  public ImageEntity(Integer id, String description, String url, ImageTypeEnum imageType) {
    this.id = id;
    this.description = description;
    this.url = url;
    this.imageType = imageType;
  }

  public ImageEntity(String description, String url, ImageTypeEnum imageType) {
    this.description = description;
    this.url = url;
    this.imageType = imageType;
  }
}