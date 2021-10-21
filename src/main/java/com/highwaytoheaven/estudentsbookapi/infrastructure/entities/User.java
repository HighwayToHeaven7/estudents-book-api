package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    private Boolean accountStatus = false;

    @ManyToOne(fetch = FetchType.LAZY)
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