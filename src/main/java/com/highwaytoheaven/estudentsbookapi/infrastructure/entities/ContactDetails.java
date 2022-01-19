package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "contact_details_table")
public class ContactDetails extends BasicEntity {

  @Column(length = 12, name = "phone_number")
  private String phoneNumber;
  private String anotherPhoneNumber;
  private String email;
  private String correspondenceAddress;
}
