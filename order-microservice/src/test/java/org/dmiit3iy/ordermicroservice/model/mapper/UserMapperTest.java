package org.dmiit3iy.ordermicroservice.model.mapper;

import org.dmiit3iy.ordermicroservice.mapper.UserMapper;
import org.dmiit3iy.ordermicroservice.model.Role;
import org.dmiit3iy.ordermicroservice.model.User;
import org.dmiit3iy.ordermicroservice.model.dto.RegistrationRequest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.*;


class UserMapperTest {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void methodShouldReturnRightUser() {
        User expectUser = new User();
        expectUser.setUsername("username");
        expectUser.setPassword("password");
        expectUser.setEmail("email");
        expectUser.setRole(Role.USER);

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setUsername("username");
        registrationRequest.setPassword("password");
        registrationRequest.setEmail("email");
        User mappedUser = userMapper.toUser(registrationRequest);
        assertThat(expectUser).isEqualTo(mappedUser);
        assertThat(Role.USER).isEqualTo(expectUser.getRole());
    }
}