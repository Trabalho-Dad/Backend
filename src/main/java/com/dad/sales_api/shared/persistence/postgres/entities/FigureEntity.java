package com.dad.sales_api.shared.persistence.postgres.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "figure")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FigureEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 150)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(precision = 10, scale = 2, nullable = false)
  private BigDecimal price;

  private Integer quantity;

  private Boolean active;

  @Column(name = "is_launch")
  private Boolean isLaunch;

  @ManyToOne()
  @JoinColumn(name = "id_character", nullable = false)
  private CharacterEntity character;

  @ManyToMany
  @JoinTable(
    name = "figure_accessory",
    joinColumns = @JoinColumn(name = "id_figure"),
    inverseJoinColumns = @JoinColumn(name = "id_accessory")
  )
  private List<AccessoryEntity> accessories;

  @ManyToMany
  @JoinTable(
    name = "figure_category",
    joinColumns = @JoinColumn(name = "id_figure"),
    inverseJoinColumns = @JoinColumn(name = "id_category")
  )
  private List<CategoryEntity> categories;

  @OneToMany(mappedBy = "figure")
  private List<UserOrderFigureEntity> orders;

  @OneToMany(mappedBy = "figure")
  private List<ImageEntity> images;

  public FigureEntity(String name, String description, BigDecimal price, Integer quantity,
      Boolean active, CharacterEntity character, List<AccessoryEntity> accessories, List<CategoryEntity> categories, List<ImageEntity> images) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
    this.active = active;
    this.character = character;
    this.accessories = accessories;
    this.categories = categories;
    this.images = images;
  }
}
