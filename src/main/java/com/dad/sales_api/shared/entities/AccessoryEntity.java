package com.dad.sales_api.shared.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accessory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccessoryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 150)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @ManyToMany(mappedBy = "accessories")
  private List<FigureEntity> figures;

  @OneToOne
  @JoinColumn(name = "id_image", unique = true)
  private ImageEntity image;
}