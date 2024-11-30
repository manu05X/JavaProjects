package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.SignupController;
import org.example.evaluations.evaluation.dtos.SignupRequestDto;
import org.example.evaluations.evaluation.exceptions.UserAlreadyExistsException;
import org.example.evaluations.evaluation.models.Client;
import org.example.evaluations.evaluation.services.SignupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SignupController.class)
public class SignupControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SignupService signupService;

    private SignupRequestDto signupRequestDto;
    private Client client;

    @BeforeEach
    void setUp() {
        signupRequestDto = new SignupRequestDto();
        signupRequestDto.setName("John Doe");
        signupRequestDto.setEmail("john.doe@example.com");
        signupRequestDto.setPassword("password123");
        signupRequestDto.setPhoneNumber("1234567890");

        client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        client.setEmail("john.doe@example.com");
        client.setPassword("password123");
        client.setPhoneNumber("1234567890");
    }

    @Test
    void testPerformSignupSuccess() throws Exception {
        when(signupService.signup(anyString(), anyString(), anyString(), anyString())).thenReturn(client);

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(signupRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(signupService).signup("john.doe@example.com", "password123", "John Doe", "1234567890");
    }

    @Test
    void testPerformSignupUserAlreadyExists() throws Exception {
        when(signupService.signup(anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new UserAlreadyExistsException("Please try out with some other email."));

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(signupRequestDto)))
                .andExpect(content().string("Please try out with some other email."));

        verify(signupService).signup("john.doe@example.com", "password123", "John Doe", "1234567890");
    }
}
