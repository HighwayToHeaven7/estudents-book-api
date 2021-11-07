package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "semester_data_table")
public class Semester extends BasicEntity {

    @Column(nullable = false)
    Integer semesterNumber;

    @Column(nullable = false)
    Date semesterStartDate;

    @Column(nullable = false)
    Date semesterEndDate;

    @Column(nullable = false)
    String groupName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Major major;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> usersList;
}
