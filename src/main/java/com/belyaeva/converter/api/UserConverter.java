package com.belyaeva.converter.api;

import com.belyaeva.dto.UserDto;
import com.belyaeva.model.Team;
import com.belyaeva.model.User;

public interface UserConverter {

    UserDto createUserDto(User user);

    User createUserModel(UserDto userDto);

    Team createTeamModel(UserDto userDto);
}
