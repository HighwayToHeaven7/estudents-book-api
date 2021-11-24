package com.highwaytoheaven.estudentsbookapi.infrastructure.mappers;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.model.ProfessorDTO;
import com.highwaytoheaven.model.SubjectResponseDTO;
import com.highwaytoheaven.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    @Mappings({
            @Mapping(target = "id", source = "user.id"),
            @Mapping(target = "personalData", source = "userDTO"),
            @Mapping(target = "subjects", source = "subjectResponseDTOList")
    })
    ProfessorDTO userToProfessorDTO(User user, UserDTO userDTO, List<SubjectResponseDTO> subjectResponseDTOList);
}
