package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "student_group_table")
public class StudentGroup extends BasicEntity {

  @Column(nullable = false, name = "group_code")
  private String groupCode;
}
