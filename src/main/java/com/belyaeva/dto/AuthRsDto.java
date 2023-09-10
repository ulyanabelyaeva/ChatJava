package com.belyaeva.dto;

public final class AuthRsDto {

    private String token;

    private UserDto user;

    public UserDto getUser() {
        return user;
    }

    public AuthRsDto setUser(UserDto userDto) {
        this.user = userDto;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AuthRsDto setToken(String token) {
        this.token = token;
        return this;
    }
}
