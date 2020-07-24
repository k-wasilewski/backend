package com.shop.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void authPathAccess_granted() throws Exception {
        //given
        String username = "kuba";
        String name = "test@test.pl";
        String password = "test";
        JwtRequest request = new JwtRequest(name, password);
        String json = new ObjectMapper().writeValueAsString(request);
        String token = null;

        //when
        MvcResult result = mockMvc.perform(post("/login")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Origin", "http://localhost:3000"))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        JwtResponse jwtresp = new ObjectMapper().readValue(response, JwtResponse.class);
        token = jwtresp.getToken();

        //then
        mockMvc.perform(get("/auth/list?username="+username)
                .header("Origin", "http://localhost:3000")
                .header("Authorization", "Bearer "+token))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void authPathAccess_denied() throws Exception {
        //given, when, then
        mockMvc.perform(get("/auth/list")
                .header("Origin", "http://localhost:3000"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
}
