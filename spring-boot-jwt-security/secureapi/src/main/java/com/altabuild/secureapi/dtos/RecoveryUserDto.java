package com.altabuild.secureapi.dtos;

import com.altabuild.secureapi.entities.Role;

import java.util.List;

public record RecoveryUserDto (
        Long id,
        String email,
        List<Role> roles
){
}
