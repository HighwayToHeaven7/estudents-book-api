package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_table")
public class User extends BasicEntity {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String surname;

  private Boolean accountStatus = false;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToOne(fetch = FetchType.LAZY)
  private ContactDetails contactDetails;

  @ManyToOne(fetch = FetchType.LAZY)
  private StudentGroup studentGroup;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Grade> gradesList;

//    @ManyToMany(fetch = FetchType.LAZY)
//    private List<Subject> subjectsList;
}