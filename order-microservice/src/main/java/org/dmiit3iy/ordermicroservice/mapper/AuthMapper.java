package org.dmiit3iy.ordermicroservice.mapper;

import org.dmiit3iy.ordermicroservice.model.dto.AuthRequest;
import org.mapstruct.Mapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    default UsernamePasswordAuthenticationToken toAuthToken(AuthRequest request) {
        return new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
    }
}