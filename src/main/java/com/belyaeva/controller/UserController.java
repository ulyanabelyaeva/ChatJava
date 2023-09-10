package com.belyaeva.controller;

import com.belyaeva.converter.api.UserConverter;
import com.belyaeva.dto.AuthRqDto;
import com.belyaeva.dto.AuthRsDto;
import com.belyaeva.dto.UserDto;
import com.belyaeva.model.User;
import com.belyaeva.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RestController
public class UserController {

    private final UserService userService;

    private final UserConverter userConverter;

    @Autowired
    public UserController(UserService userService,
                          UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping("/registration/participant")
    public ResponseEntity<Void> createNewParticipant(@RequestBody UserDto userDto){
        userService.createNewUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/registration/team")
    public ResponseEntity<Void> createNewTeam(@RequestBody UserDto userDto){
        userService.createNewTeam(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthRsDto> login(@RequestBody AuthRqDto authRqDto){
        try{
            String token = userService.getToken(authRqDto);
            User authUser = userService.getUserByLogin(authRqDto.getLogin());

            AuthRsDto authRsDto = new AuthRsDto()
                    .setUser(userConverter.createUserDto(authUser))
                    .setToken(token);

            return ResponseEntity.ok(authRsDto);
        } catch (AuthenticationException authenticationException){
            throw new BadCredentialsException("Invalid login or password");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(){
        User currentUser = userService.getCurrentUser();
        if (isNull(currentUser)){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        UserDto userDto = userConverter.createUserDto(currentUser);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
