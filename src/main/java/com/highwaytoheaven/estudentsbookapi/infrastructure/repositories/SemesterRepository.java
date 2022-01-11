package com.highwaytoheaven.estudentsbookapi.infrastructure.repositories;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Semester;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SemesterRepository extends JpaRepository<Semester, UUID> {

    @Query("SELECT s FROM Semester s WHERE :now >= s.semesterStartDate AND :now <= s.semesterEndDate")
    List<Semester> getAllByDate(@Param("now")Date now);

    @Query("SELECT s FROM Semester s WHERE :now >= s.semesterStartDate AND :now <= s.semesterEndDate " +
            "AND s.groupName = :groupName AND :student member s.usersList")
    Optional<Semester> getSemesterByGroupNameAndDateAndStudent(@Param("groupName")String groupName,
                                                     @Param("now")Date now,
                                                     @Param("student")User student);

    @Query("SELECT s FROM Semester s WHERE :now >= s.semesterStartDate AND :now <= s.semesterEndDate " +
            "AND :student member s.usersList")
    Optional<Semester> getSemesterByDateAndStudent(@Param("now")Date now,  @Param("student")User student);


    @Query("SELECT MAX(s.semesterNumber) FROM Semester s WHERE :student member s.usersList")
    Optional<Integer> getLatestSemesterNumberByUser(@Param("student")User student);


    @Query("SELECT s FROM Semester s WHERE :student member s.usersList AND s.semesterNumber = :semesterNumber")
    Optional<Semester> getLatestSemesterByStudentAndSemesterNumber(@Param("student")User student,
                                                                   @Param("semesterNumber")Integer semesterNumber);


    Optional<Semester> getSemesterBySemesterNumberAndUsersListContaining(Integer semesterNumber, User user);
    Optional<Semester> getSemesterBySemesterEndDateGreaterThanEqualAndSemesterStartDateLessThanEqualAndUsersListContaining(Date now, Date now1, User user);
}
