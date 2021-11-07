package com.highwaytoheaven.estudentsbookapi.infrastructure.mappers;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.model.StudentDTO;
import com.highwaytoheaven.model.UserContactDTO;
import com.highwaytoheaven.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "user.id", target = "id"),
            @Mapping(source = "user.name", target = "name"),
            @Mapping(source = "user.surname", target = "surname"),
            @Mapping(source = "user.role", target = "role"),
            @Mapping(source = "userContactDTO", target = "contact")
    })
    UserDTO userEntityToUserDto(User user, UserContactDTO userContactDTO);

//    @Mappings({
//            @Mapping(source = "user.id", target = "id"),
//            @Mapping(source = "user.name", target = "name"),
//            @Mapping(source = "user.surname", target = "surname"),
//            @Mapping(source = "user.accountStatus", target = "role"),
//            @Mapping(source = "user.role", target = "role")
//    })
    StudentDTO userToStudentDTO(User user); //TODO fix studentDto, remove -> currentSemester, studentStatus


}
