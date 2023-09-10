package com.belyaeva.converter.impl;

import com.belyaeva.converter.api.UserConverter;
import com.belyaeva.dto.UserDto;
import com.belyaeva.model.Team;
import com.belyaeva.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserConverterImpl implements UserConverter {

    @Override
    public UserDto createUserDto(User user) {
        return new UserDto()
                .setId(String.valueOf(user.getId()))
                .setName(user.getName())
                .setLogin(user.getLogin())
                .setTeam(user.getTeam().getName());
    }

    @Override
    public User createUserModel(UserDto userDto) {
        return new User()
                .setName(userDto.getName())
                .setPassword(userDto.getPassword())
                .setLogin(userDto.getLogin());
    }

    @Override
    public Team createTeamModel(UserDto userDto) {
        return new Team().setName(userDto.getTeam());
    }
}
