package org.example.evaluations.controllers;

import org.example.evaluations.evaluation.controllers.AuthController;
import org.example.evaluations.evaluation.dtos.LoginRequestDto;
import org.example.evaluations.evaluation.dtos.ValidateTokenRequestDto;
import org.example.evaluations.evaluation.services.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
public class AuthControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    public void testLogin() throws Exception {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail("test@example.com");
        loginRequestDto.setPassword("password123");

        when(authenticationService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword())).thenReturn(ResponseEntity.ok(true));

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(authenticationService).login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

    @Test
    public void testValidateToken() throws Exception {
        ValidateTokenRequestDto validateTokenRequestDto = new ValidateTokenRequestDto();
        validateTokenRequestDto.setEmail("test@example.com");
        validateTokenRequestDto.setToken("someToken");

        when(authenticationService.validateToken(validateTokenRequestDto.getEmail(), validateTokenRequestDto.getToken())).thenReturn(true);

        mockMvc.perform(post("/validateToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"token\":\"someToken\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(authenticationService).validateToken(validateTokenRequestDto.getEmail(), validateTokenRequestDto.getToken());
    }
}
