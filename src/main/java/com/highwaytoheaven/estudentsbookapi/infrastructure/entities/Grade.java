package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import javax.persistence.*;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.GradeType;
import com.sun.istack.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "grade_table")
public class Grade extends BasicEntity {

  @Column(nullable = false)
  private Double value;

  @Column(nullable = false)
  private Double weight;

  private String description;

  @Enumerated(EnumType.STRING)
  private GradeType gradeType;

  @ManyToOne(fetch = FetchType.EAGER)
  private SubjectCard subjectCard;
}
