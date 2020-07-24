package com.shop.backend.security;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JwtTokenUtilTest {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    JwtUserDetailsService jwtUserDetailsService;
    private final String username = "test@test.pl";

    @Test
    public void testA_generateToken_shouldReturnToken() {
        //given
        UserDetails details=jwtUserDetailsService.loadUserByUsername(username);

        //when
        String token = jwtTokenUtil.generateToken(details);

        //then
        assertNotNull(token);
    }

    @Test
    public void testB_getUsernameFromToken_shouldReturnExistingUsername() {
        //given
        UserDetails details=jwtUserDetailsService.loadUserByUsername(username);

        //when
        String token = jwtTokenUtil.generateToken(details);
        String resultUsername = jwtTokenUtil.getUsernameFromToken(token);

        //then
        assertEquals(username, resultUsername);
    }

    @Test
    public void testC_getExpirationDateFromToken_shouldReturnFutureDate() {
        //given
        UserDetails details=jwtUserDetailsService.loadUserByUsername(username);

        //when
        String token = jwtTokenUtil.generateToken(details);
        Date resultDate = jwtTokenUtil.getExpirationDateFromToken(token);

        //then
        assertTrue(resultDate.after(new Date()));
    }

    @Test
    public void testD_validateToken_shouldReturnTrue() {
        //given
        UserDetails details=jwtUserDetailsService.loadUserByUsername(username);

        //when
        String token = jwtTokenUtil.generateToken(details);
        boolean isValid = jwtTokenUtil.validateToken(token, details);

        //then
        assertTrue(isValid);
    }
}
