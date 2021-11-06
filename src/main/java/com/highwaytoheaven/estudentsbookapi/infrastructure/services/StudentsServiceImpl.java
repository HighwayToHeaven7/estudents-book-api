package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.application.services.StudentsService;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.SemesterRepository;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.UserRepository;
import com.highwaytoheaven.model.GradeDTO;
import com.highwaytoheaven.model.GradeUpdateRequestDTO;
import com.highwaytoheaven.model.GroupWithStudentsResponseDTO;
import com.highwaytoheaven.model.StudentDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class StudentsServiceImpl implements StudentsService {

    private final UserRepository userRepository;
    private final SemesterRepository semesterRepository;

    @Override
    public List<GroupWithStudentsResponseDTO> getListOfGroupsWithStudents() throws Exception {
        List<User> students = userRepository.getAllActiveStudents();
        return null;
    }

    @Override
    public List<StudentDTO> getStudentById(UUID uuid) {
        return null;
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
