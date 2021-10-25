package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "semester")
public class Semester extends BasicEntity {

    @Column(nullable = false)
    Integer number;

    @ManyToOne(fetch = FetchType.EAGER)
    private Major major;
}
