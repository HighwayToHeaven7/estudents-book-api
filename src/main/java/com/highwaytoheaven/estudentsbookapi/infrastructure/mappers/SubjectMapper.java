package com.highwaytoheaven.estudentsbookapi.infrastructure.mappers;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Subject;
import com.highwaytoheaven.model.Student1DTO;
import com.highwaytoheaven.model.SubjectDTO;
import com.highwaytoheaven.model.SubjectDetailsDTO;
import com.highwaytoheaven.model.SubjectResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mappings({
            @Mapping(source = "subject.id", target = "id"),
            @Mapping(source = "subject.name", target = "subjectName"),
            @Mapping(source = "student1DTOList", target = "students")
    })
    SubjectResponseDTO subjectToSubjectResponseDTO(Subject subject, List<Student1DTO> student1DTOList);

    @Mappings({
            @Mapping(source = "subject.id", target = "id"),
            @Mapping(source = "subject.name", target = "subjectName")
    })
    SubjectDTO subjectToSubjectDTO(Subject subject);

    @Mappings({
            @Mapping(source = "subject.id", target = "uuid"),
            @Mapping(source = "subject.name", target = "subjectName"),
            @Mapping(source = "subject.user.id", target = "professorUuid")
    })
    SubjectDetailsDTO subjectToSubjectDetailsDTO(Subject subject);
}
