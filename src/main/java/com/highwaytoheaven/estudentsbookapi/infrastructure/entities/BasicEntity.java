package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@MappedSuperclass
@Getter
@Setter
public class BasicEntity implements Serializable {

//  @Id
//  @GeneratedValue(generator = "UUID")
//  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//  @Column(name = "id", updatable = false, nullable = false)
//  private UUID id;

  @Id
  @GeneratedValue(generator = "uuid4")
  @GenericGenerator(name = "UUID", strategy = "uuid4")
  @Type(type = "org.hibernate.type.UUIDCharType")
  @Column(columnDefinition = "CHAR(36)")
  private UUID id;
}
