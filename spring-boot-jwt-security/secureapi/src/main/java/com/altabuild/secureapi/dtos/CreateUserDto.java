package com.altabuild.secureapi.dtos;

import com.altabuild.secureapi.entities.RoleName;

public record CreateUserDto(
        String email,
        String password,
        RoleName role
) {
}
