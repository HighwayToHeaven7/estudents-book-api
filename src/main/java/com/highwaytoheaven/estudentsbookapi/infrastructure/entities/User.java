package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import javax.persistence.*;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.Role;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.UserStatus;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user_table")
public class User extends BasicEntity {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String surname;

//  @Column(name = "account_status")
  @Enumerated(EnumType.STRING)
  private UserStatus accountStatus = UserStatus.NEW;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToOne(fetch = FetchType.LAZY)
  private ContactDetails contactDetails;

//  @NotNull
//  @ManyToOne(fetch = FetchType.EAGER)
//  private StudentGroup studentGroup;

//  @Column(nullable = true)
//  private String groupName;

//  @Column(nullable = true)
//  @ManyToMany(fetch = FetchType.LAZY)
//  private List<Semester> semesters;
}