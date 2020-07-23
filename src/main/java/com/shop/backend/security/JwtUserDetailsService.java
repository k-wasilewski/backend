package com.shop.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Component
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (!username.equals("test@test.pl") && !username.equals("kuba@gmail.com"))
            throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("user"));

        String encodedPasswordTest = passwordEncoder.encode("test");
        String encodedPasswordKuba = passwordEncoder.encode("kuba");

        if (username.equals("test@test.pl"))
            return new org.springframework.security.core.userdetails.User(
                    username, encodedPasswordTest, grantedAuthorities);
        else if (username.equals("kuba@gmail.com"))
            return new org.springframework.security.core.userdetails.User(
                    username, encodedPasswordKuba, grantedAuthorities);

        return null;
    }
}
