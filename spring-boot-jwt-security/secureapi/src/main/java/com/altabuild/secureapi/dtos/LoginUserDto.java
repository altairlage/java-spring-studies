package com.altabuild.secureapi.dtos;

public record LoginUserDto(
        String email,
        String password
) {
}
