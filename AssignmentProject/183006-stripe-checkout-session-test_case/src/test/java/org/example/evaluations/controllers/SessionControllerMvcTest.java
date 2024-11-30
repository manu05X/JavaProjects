package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.SessionController;
import org.example.evaluations.evaluation.dtos.CreateSessionDto;
import org.example.evaluations.evaluation.dtos.SessionDto;
import org.example.evaluations.evaluation.services.ISessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
public class SessionControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ISessionService sessionService;

    @Test
    void testCreateSession() throws Exception {
        // Arrange
        CreateSessionDto createSessionDto = new CreateSessionDto();
        createSessionDto.setSuccessUrl("http://success.url");
        createSessionDto.setAmounts(Arrays.asList(105L, 250L));
        createSessionDto.setProductNames(Arrays.asList("Product1", "Product2"));
        createSessionDto.setQuantities(Arrays.asList(1L, 2L));

        SessionDto expectedSessionDto = new SessionDto();
        expectedSessionDto.setId("sess_id__090901221_dsds");
        expectedSessionDto.setTotal(605L);
        when(sessionService.createSession(createSessionDto.getSuccessUrl(),
                createSessionDto.getAmounts(),
                createSessionDto.getProductNames(),
                createSessionDto.getQuantities()))
                .thenReturn(expectedSessionDto);

        // Act & Assert
        mockMvc.perform(post("/session")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createSessionDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedSessionDto)));
    }


}
