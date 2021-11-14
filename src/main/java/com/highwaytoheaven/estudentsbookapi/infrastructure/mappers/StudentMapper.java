package com.highwaytoheaven.estudentsbookapi.infrastructure.mappers;


import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Semester;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mappings({
            @Mapping(target = "groupName", source = "semester.groupName"),
            @Mapping(target = "semesterNumber", source = "semester.semesterNumber"),
            @Mapping(target = "students", source = "listOfStudentDTO")
    })
    GroupWithStudentsResponseDTO semesterToGroupWithStudentsResponseDTO(Semester semester,
                                                                        List<StudentDTO> listOfStudentDTO);

    @Mappings({
            @Mapping(target = "id", source = "user.id"),
            @Mapping(target = "personalData", source = "userDTO"),
            @Mapping(target = "subjectCards", source = "subjectCards")
    })
    StudentDTO userToStudentDTO(User user, UserDTO userDTO, List<StudentSubjectCardResponseDTO> subjectCards);
}
