package com.belyaeva.security;

import com.belyaeva.model.User;
import com.belyaeva.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);

        if (isNull(user)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", login));
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}
