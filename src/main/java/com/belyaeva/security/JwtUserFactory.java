package com.belyaeva.security;

import com.belyaeva.model.User;

public final class JwtUserFactory {

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getLogin(),
                user.getPassword()
        );
    }
}
