package com.shop.backend.controllers;

import com.shop.backend.entities.Order;
import com.shop.backend.security.JwtRequest;
import com.shop.backend.security.JwtResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtAuthenticationControllerTest {
    @Autowired
    JwtAuthenticationController jwtAuthenticationController;

    @Test
    public void createAuthenticationToken_shouldReturnAuthToken_whenCredentialsAreValid() {
        //given
        String username = "test@test.pl";
        String password = "test";
        JwtRequest request = new JwtRequest(username, password);

        //when
        JwtResponse response=null;
        try {
            response = (JwtResponse) jwtAuthenticationController
                    .createAuthenticationToken(request).getBody();
        } catch (Exception e) {assertEquals(1, 2);}

        //then
        assertNotNull(response.getToken());
    }

    @Test
    public void createAuthenticationToken_shouldThrowException_whenCredentialsAreInalid() {
        //given
        String username = "gergre@test.pl";
        String password = "dwe";
        JwtRequest request = new JwtRequest(username, password);

        //when, then
        JwtResponse response=null;
        try {
            response = (JwtResponse) jwtAuthenticationController
                    .createAuthenticationToken(request).getBody();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "INVALID_CREDENTIALS");
        }
    }
}
