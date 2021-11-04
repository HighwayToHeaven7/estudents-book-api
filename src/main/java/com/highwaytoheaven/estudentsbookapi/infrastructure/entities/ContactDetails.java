package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "contact_details_table")
public class ContactDetails extends BasicEntity {

  @Column(nullable = false, unique = true)
  private String email;

  @Column(length = 12, name = "phone_number")
  private String phoneNumber;
}
