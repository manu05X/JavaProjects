package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.InstallController;
import org.example.evaluations.evaluation.dtos.InstallRequestDto;
import org.example.evaluations.evaluation.dtos.InstallResponseDto;
import org.example.evaluations.evaluation.models.InstallStatus;
import org.example.evaluations.evaluation.services.IDeployService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InstallController.class)
public class InstallControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDeployService deployService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testInstallSuccess() throws Exception {
        UUID appId = UUID.randomUUID();
        InstallStatus installStatus = InstallStatus.INSTALL_SUCCESS;

        InstallRequestDto requestDto = new InstallRequestDto();
        requestDto.setAppId(appId);

        InstallResponseDto responseDto = new InstallResponseDto();
        responseDto.setStatus(installStatus);
        responseDto.setAppId(appId);

        when(deployService.install(appId)).thenReturn(installStatus);


        mockMvc.perform(post("/install")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(responseDto)))
                .andExpect(jsonPath("$.status").value(installStatus.toString()))
                .andExpect(jsonPath("$.appId").value(appId.toString()));
    }

    @Test
    public void testInstallAppIdNull() throws Exception {
        InstallRequestDto requestDto = new InstallRequestDto();
        requestDto.setAppId(null);

        mockMvc.perform(post("/install")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"appId\": null}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("AppId is null"));
    }

    @Test
    public void testInstallInvalidRequest() throws Exception {
        mockMvc.perform(post("/install")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
