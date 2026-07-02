package com.dad.sales_api.shared.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "character")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CharacterEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 150)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @OneToMany(mappedBy = "character")
  private List<FigureEntity> figures;

  @OneToMany(mappedBy = "character")
  private List<ImageEntity> images;

  private Boolean active = true;

  public CharacterEntity(Integer id, String name, String description, Boolean active){
    this.id = id;
    this.name = name;
    this.description = description;
    this.active = active;
  }

  public CharacterEntity(String name, String description, Boolean active, List<ImageEntity> entities){
    this.name = name;
    this.description = description;
    this.active = active != null ? active : this.active;
    this.images = entities;
  }
}
