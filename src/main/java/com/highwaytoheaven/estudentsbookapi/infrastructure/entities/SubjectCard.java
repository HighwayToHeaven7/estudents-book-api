package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
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
}
