package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "student_group_table")
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //exp: A, B, F
    @Column(nullable = false)
    private String groupCode;
}
