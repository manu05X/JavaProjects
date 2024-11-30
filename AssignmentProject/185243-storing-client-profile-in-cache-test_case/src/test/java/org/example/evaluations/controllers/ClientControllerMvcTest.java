package org.example.evaluations.controllers;

import org.example.evaluations.evaluation.controllers.ClientController;
import org.example.evaluations.evaluation.exceptions.UserNotFoundException;
import org.example.evaluations.evaluation.models.Client;
import org.example.evaluations.evaluation.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ClientControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        client.setEmail("john.doe@example.com");
    }

    @Test
    void testGetClientFromIdSuccess() throws Exception {
        when(clientService.getClientFromId(1L)).thenReturn(client);

        mockMvc.perform(get("/client/id/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(clientService).getClientFromId(1L);
    }

    @Test
    void testGetClientFromIdNotFound() throws Exception {
        when(clientService.getClientFromId(any(Long.class))).thenThrow(new UserNotFoundException("Please signup first"));

        mockMvc.perform(get("/client/id/{id}", 1L))
                .andExpect(content().string("Please signup first"));

        verify(clientService).getClientFromId(1L);
    }

    @Test
    void testGetClientFromEmailSuccess() throws Exception {
        when(clientService.getClientFromEmail("john.doe@example.com")).thenReturn(client);

        mockMvc.perform(get("/client/email/{email}", "john.doe@example.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(clientService).getClientFromEmail("john.doe@example.com");
    }

    @Test
    void testGetClientFromEmailNotFound() throws Exception {
        when(clientService.getClientFromEmail("john.doe@example.com")).thenThrow(new UserNotFoundException("Please signup first"));

        mockMvc.perform(get("/client/email/{email}", "john.doe@example.com"))
                .andExpect(content().string("Please signup first"));

        verify(clientService).getClientFromEmail("john.doe@example.com");
    }
}
