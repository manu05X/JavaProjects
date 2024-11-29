package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.AuthController;
import org.example.evaluations.evaluation.dtos.LoginRequest;
import org.example.evaluations.evaluation.dtos.LoginResponse;
import org.example.evaluations.evaluation.dtos.SignupRequest;
import org.example.evaluations.evaluation.dtos.SignupResponse;
import org.example.evaluations.evaluation.exceptions.BadCredentialsException;
import org.example.evaluations.evaluation.exceptions.UserNotSignedUpException;
import org.example.evaluations.evaluation.services.IAuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(AuthController.class)
public class AuthControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSignup() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFirstName("firstName");
        signupRequest.setLastName("lastName");
        signupRequest.setAddress("address");
        signupRequest.setPhoneNumber("phoneNumber");
        signupRequest.setPassword("password");
        signupRequest.setEmail("email");
        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setLastName("lastName");
        signupResponse.setFirstName("firstName");
        signupResponse.setEmail("email");

        Mockito.when(authService.signup(signupRequest)).thenReturn(signupResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("firstName"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testLogin_Success() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("email");
        loginRequest.setPassword("password");
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken("token");
        loginResponse.setTokenValidity(new Date(System.currentTimeMillis()+172800000));
        loginResponse.setUserEmail("email");

        Mockito.when(authService.login(loginRequest)).thenReturn(loginResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("token"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userEmail").value("email"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tokenValidity").exists())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testLogin_BadCredentials() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("email");
        loginRequest.setPassword("wrong_password");

        Mockito.when(authService.login(loginRequest)).thenThrow(new BadCredentialsException("Please provide correct password !!"));

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Please provide correct password !!"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testLogin_UserNotSignedUp() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("unknown_email");
        loginRequest.setPassword("password");

        Mockito.when(authService.login(loginRequest)).thenThrow(new UserNotSignedUpException("Please sign up first !!"));

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Please sign up first !!"))
                .andDo(MockMvcResultHandlers.print());
    }
}
