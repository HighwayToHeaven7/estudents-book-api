package com.highwaytoheaven.estudentsbookapi.infrastructure.mappers;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.ContactDetails;
import com.highwaytoheaven.model.UserContactDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface ContactDetailsMapper {

    @Mappings({
            @Mapping(target = "email", source = "contactDetails.email"),
            @Mapping(target = "phoneNumber", source = "contactDetails.phoneNumber")
    })
    UserContactDTO contactDetailsToUserContactDto(ContactDetails contactDetails);
}
