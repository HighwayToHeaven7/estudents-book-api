package com.highwaytoheaven.estudentsbookapi.infrastructure.mappers;


import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Semester;
import com.highwaytoheaven.model.GroupWithStudentsResponseDTO;
import com.highwaytoheaven.model.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SemesterMapper {

    @Mappings({
            @Mapping(target = "groupName", source = "semester.groupName"),
            @Mapping(target = "students", source = "studentDtoList")
    })
    GroupWithStudentsResponseDTO semesterToGroupWithStudentsResponseDTO(Semester semester,
                                                                        List<StudentDTO> studentDtoList);
}
