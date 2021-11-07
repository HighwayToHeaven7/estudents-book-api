package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.application.services.StudentsService;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Semester;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.SemesterMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.UserMapper;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.SemesterRepository;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.UserRepository;
import com.highwaytoheaven.model.GradeDTO;
import com.highwaytoheaven.model.GradeUpdateRequestDTO;
import com.highwaytoheaven.model.GroupWithStudentsResponseDTO;
import com.highwaytoheaven.model.StudentDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentsServiceImpl implements StudentsService {

    private final UserRepository userRepository;
    private final SemesterRepository semesterRepository;
    private final SemesterMapper semesterMapper;
    private final UserMapper userMapper;

    @Override
    public List<GroupWithStudentsResponseDTO> getListOfGroupsWithStudents() {
       List<Semester> semesters = semesterRepository.getAllBySemesterDate(new Date(System.currentTimeMillis()));

        return semesters.stream().map(semester -> {
            return semesterMapper.semesterToGroupWithStudentsResponseDTO(semester,
                    semester.getUsersList().stream().map(userMapper::userToStudentDTO)
                            .collect(Collectors.toList()));
        }).collect(Collectors.toList());

    }

    @Override
    public List<StudentDTO> getStudentById(UUID uuid) {
        return userRepository.getAllById(uuid).stream().map(userMapper::userToStudentDTO).collect(Collectors.toList());
    }

    @Override
    public List<GradeDTO> getStudentGradesByStudentId(UUID uuid, Optional<Integer> optional) {
        return null;
    }

    @Override
    public GradeDTO giveStudentGrade(UUID uuid, UUID uuid1, GradeUpdateRequestDTO gradeUpdateRequestDTO) {
        return null;
    }
}
