package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subject_card")
public class SubjectCard extends BasicEntity{

    @Column(nullable = true)
    private Double expectedFinalGrade;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Semester semester;

//    @OneToMany(fetch = FetchType.LAZY)
//    List<Grade> gradeList;
}
