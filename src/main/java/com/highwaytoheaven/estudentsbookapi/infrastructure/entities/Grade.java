package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "grade_table")
public class Grade extends BasicEntity {


  private Integer value;

  // 1 < weight < ?
  private Integer weight;

  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  private Subject subject;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
}
