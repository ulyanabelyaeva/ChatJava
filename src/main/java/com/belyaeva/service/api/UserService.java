package com.belyaeva.service.api;

import com.belyaeva.dto.AuthRqDto;
import com.belyaeva.dto.UserDto;
import com.belyaeva.model.User;

import java.util.List;

public interface UserService {

    void createNewUser(UserDto userDto);

    User getCurrentUser();

    User getUserByLogin(String login);

    String getToken(AuthRqDto authRqDto);

    void createNewTeam(UserDto userDto);

    User getUserById(Long id);
}
