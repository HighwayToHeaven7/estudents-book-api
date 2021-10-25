package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

  @Column(nullable = false, name = "account_status")
  private Boolean accountStatus = false;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToOne(fetch = FetchType.LAZY)
  private ContactDetails contactDetails;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  private StudentGroup studentGroup;

  @Column(nullable = false)
  @ManyToMany(fetch = FetchType.LAZY)
  private List<Semester> semesters;
}