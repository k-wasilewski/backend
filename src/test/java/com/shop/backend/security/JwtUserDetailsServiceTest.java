package com.shop.backend.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtUserDetailsServiceTest {
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void loadUserByUsername_shouldReturnUserDetails_whenExistingUsernameIsPassed() {
        //given
        String existingUsername = "test@test.pl";

        //when
        UserDetails result = jwtUserDetailsService.loadUserByUsername(existingUsername);

        //then
        assertEquals(existingUsername, result.getUsername());
        assertNotNull(result.getPassword());
    }

    @Test
    public void loadUserByUsername_shouldThrowException_whenNonExistingUsernameIsPassed() {
        //given
        String nonExistingUsername = "dsfsd";

        //when, then
        assertThrows(UsernameNotFoundException.class, () ->
                jwtUserDetailsService.loadUserByUsername(nonExistingUsername));
    }
}
