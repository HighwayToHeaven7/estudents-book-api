package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "major_table")
public class Major extends BasicEntity {

  @Column(nullable = false)
  private String name;

  @ManyToMany(fetch = FetchType.LAZY)
  private List<Subject> subjectsList;
}
