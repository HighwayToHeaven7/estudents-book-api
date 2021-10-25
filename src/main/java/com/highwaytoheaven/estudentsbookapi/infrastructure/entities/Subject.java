package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "subject_table")
public class Subject extends BasicEntity {

  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
}
