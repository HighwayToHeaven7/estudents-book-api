package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "grade_table")
public class Grade extends BasicEntity {

  @Column(columnDefinition = "INT(1) UNSIGNED NOT NULL")
  private Integer value;

  @Column(columnDefinition = "INT(1) UNSIGNED NOT NULL")
  private Integer weight;

  private String description;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  private Subject subject;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  private Semester semester;
}
