package com.highwaytoheaven.estudentsbookapi.infrastructure.mappers;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.model.StudentDTO;
import com.highwaytoheaven.model.StudentSubjectCardResponseDTO;
import com.highwaytoheaven.model.UserAuthPostResponseDTO;
import com.highwaytoheaven.model.UserContactDTO;
import com.highwaytoheaven.model.UserDTO;
import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring", uses = {ContactDetailsMapper.class})
public interface UserMapper {

  UserDTO toApi(User user);

  default StudentDTO toApi(User user,
      List<StudentSubjectCardResponseDTO> cards) {
    StudentDTO studentDTO = new StudentDTO();
    studentDTO.setId(user.getId());
    studentDTO.setPersonalData(toApi(user));
    studentDTO.setSubjectCards(cards);
    return studentDTO;
  }

  @Mappings({
      @Mapping(source = "user.id", target = "id"),
      @Mapping(source = "user.name", target = "name"),
      @Mapping(source = "user.surname", target = "surname"),
      @Mapping(source = "user.email", target = "email"),
      @Mapping(source = "user.role", target = "role"),
      @Mapping(source = "userContactDTO", target = "contact")
  })
  UserDTO userEntityToUserDto(User user, UserContactDTO userContactDTO);

  @Mappings({
      @Mapping(source = "uuid", target = "userUuid"),
      @Mapping(source = "email", target = "email"),
      @Mapping(source = "token", target = "token")
  })
  UserAuthPostResponseDTO userDetailsToUserAuthPostResponseDTO(UUID uuid, String email,
      String token);
}
