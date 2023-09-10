package com.belyaeva.service.impl;

import com.belyaeva.converter.api.UserConverter;
import com.belyaeva.dto.AuthRqDto;
import com.belyaeva.dto.UserDto;
import com.belyaeva.exception.NotFoundTeamException;
import com.belyaeva.exception.NotFoundUserByIdException;
import com.belyaeva.model.Chat;
import com.belyaeva.model.Team;
import com.belyaeva.model.User;
import com.belyaeva.repository.ChatRepository;
import com.belyaeva.repository.TeamRepository;
import com.belyaeva.repository.UserRepository;
import com.belyaeva.security.JwtTokenProvider;
import com.belyaeva.service.api.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class UserServiceImpl implements UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public UserServiceImpl(UserConverter userConverter,
                           UserRepository userRepository,
                           ChatRepository chatRepository,
                           TeamRepository teamRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        this.teamRepository = teamRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Transactional
    public void createNewUser(UserDto userDto) {
        User user = userConverter.createUserModel(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Team team = teamRepository.findByName(userDto.getTeam());
        if (isNull(team)){
            throw new NotFoundTeamException();
        }
        user.setTeam(team);
        userRepository.save(user);

        createChats(user, team);
    }

    private void createChats(User newUser,
                             Team team) {
        List<User> participants = team.getParticipants();
        List<Chat> chats = new ArrayList<>();
        for (User participant: participants) {
            Chat newChat = new Chat();
            newChat.setUsers(List.of(participant, newUser));
            participant.getChats().add(newChat);
            newUser.getChats().add(newChat);
            chats.add(newChat);
        }
        chatRepository.saveAll(chats);
    }

    @Override
    public User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal.equals("anonymousUser"))
            return null;
        else{
            UserDetails userDetails = (UserDetails) principal;
            return getUserByLogin(userDetails.getUsername());
        }
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public String getToken(AuthRqDto authRqDto) {
        String login = authRqDto.getLogin();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                login,
                authRqDto.getPassword()));

        return jwtTokenProvider.createToken(login);
    }

    @Override
    public void createNewTeam(UserDto userDto) {
        User owner = userConverter.createUserModel(userDto);
        owner.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Team team = userConverter.createTeamModel(userDto);
        team.setOwner(owner);
        owner.setTeam(team);

        teamRepository.save(team);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            throw new NotFoundUserByIdException();
        }
        return userOptional.get();
    }
}
