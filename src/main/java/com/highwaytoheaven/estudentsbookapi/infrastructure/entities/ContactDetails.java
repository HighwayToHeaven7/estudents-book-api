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
@Table(name = "contact_details_table")
public class ContactDetails extends BasicEntity {


  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = true)
  private String phoneNumber;
}
