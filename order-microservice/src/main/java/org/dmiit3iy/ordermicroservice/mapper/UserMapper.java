package org.dmiit3iy.ordermicroservice.mapper;

import org.dmiit3iy.ordermicroservice.model.User;
import org.dmiit3iy.ordermicroservice.model.dto.RegistrationRequest;
import org.dmiit3iy.ordermicroservice.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "role", constant = "USER")
    User toUser(RegistrationRequest registrationRequest);

    User updateUser(User user);

    UserDTO toUserDTO(User user);


}