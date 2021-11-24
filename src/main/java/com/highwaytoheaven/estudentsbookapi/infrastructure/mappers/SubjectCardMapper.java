package com.highwaytoheaven.estudentsbookapi.infrastructure.mappers;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.SubjectCard;
import com.highwaytoheaven.model.GradeDTO;
import com.highwaytoheaven.model.StudentSubjectCardResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectCardMapper {

    @Mappings({
            @Mapping(target = "groupName", source = "subjectCard.semester.groupName"),
            @Mapping(target = "semesterNumber", source = "subjectCard.semester.semesterNumber"),
            @Mapping(target = "subjectName", source = "subjectCard.subject.name"),
            @Mapping(target = "expectedGrade", source = "subjectCard.expectedFinalGrade"),
            @Mapping(target = "grades", source = "gradeDTOList")
    })
    StudentSubjectCardResponseDTO subjectCardToStudentSubjectCardResponseDTO(SubjectCard subjectCard, List<GradeDTO> gradeDTOList);

}
