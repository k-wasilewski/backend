package com.shop.backend.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;

@Transactional
@Component
public class JwtUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) {
        if (!username.equals("test@test.pl"))
            throw new UsernameNotFoundException(username);

        return new org.springframework.security.core.userdetails.User(
                "test@test.pl", "test", new HashSet<>());
    }
}
