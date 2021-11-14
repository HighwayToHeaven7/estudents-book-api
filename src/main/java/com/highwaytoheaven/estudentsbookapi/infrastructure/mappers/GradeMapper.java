package com.highwaytoheaven.estudentsbookapi.infrastructure.mappers;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Grade;
import com.highwaytoheaven.model.GradeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    @Mappings({
            @Mapping(target = "id", source = "grade.id"),
            @Mapping(target = "grade", source = "grade.value"),
            @Mapping(target = "weight", source = "grade.weight"),
            @Mapping(target = "description", source = "grade.description")
    })
    GradeDTO gradeToGradeDTO(Grade grade);
}
