package org.example.evaluations.controllers;

import org.example.evaluations.evaluation.controllers.AuthController;
import org.example.evaluations.evaluation.dtos.FakeStoreLoginRequestDto;
import org.example.evaluations.evaluation.services.IAuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
public class AuthControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAuthService authService;

    @Test
    void testLogin_Success() throws Exception {
        // Arrange
        FakeStoreLoginRequestDto requestDto = new FakeStoreLoginRequestDto();
        requestDto.setUsername("test");
        requestDto.setPassword("password");

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        when(authService.login(any(FakeStoreLoginRequestDto.class))).thenReturn(headers);

        // Act & Assert
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"test\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("login successful"));
    }

    @Test
    void testLogin_Failure() throws Exception {
        // Arrange
        FakeStoreLoginRequestDto requestDto = new FakeStoreLoginRequestDto();
        requestDto.setUsername("test");
        requestDto.setPassword("wrong-password");

        // No headers returned
        when(authService.login(any(FakeStoreLoginRequestDto.class))).thenReturn(null);

        // Act & Assert
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"test\",\"password\":\"wrong-password\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("login failed"))
                .andExpect(header().doesNotExist(HttpHeaders.SET_COOKIE));
    }
}
