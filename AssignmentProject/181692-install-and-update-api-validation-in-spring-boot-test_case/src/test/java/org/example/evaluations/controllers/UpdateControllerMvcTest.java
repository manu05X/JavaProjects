package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.UpdateController;
import org.example.evaluations.evaluation.dtos.UpdateRequestDto;
import org.example.evaluations.evaluation.dtos.UpdateResponseDto;
import org.example.evaluations.evaluation.models.CheckUpdateStatus;
import org.example.evaluations.evaluation.models.UpdateStatus;
import org.example.evaluations.evaluation.models.Version;
import org.example.evaluations.evaluation.services.IDeployService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UpdateController.class)
public class UpdateControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDeployService deployService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testUpdateSuccess() throws Exception {
        UUID appId = UUID.randomUUID();
        String version = "0.0.5";
        UpdateStatus updateStatus = UpdateStatus.UPDATE_SUCCESS;

        when(deployService.update(any(UUID.class), anyString())).thenReturn(updateStatus);

        UpdateRequestDto updateRequestDto = new UpdateRequestDto();
        updateRequestDto.setAppId(appId);
        updateRequestDto.setVersion(version);

        UpdateResponseDto updateResponseDto = new UpdateResponseDto();
        updateResponseDto.setStatus(updateStatus);
        updateResponseDto.setAppId(appId);
        updateResponseDto.setVersion(new Version(version));

        mockMvc.perform(post("/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(updateResponseDto)))
                .andExpect(jsonPath("$.appId").value(appId.toString()))
                .andExpect(jsonPath("$.version.value").value(version))
                .andExpect(jsonPath("$.status").value(updateStatus.toString()));
    }

    @Test
    public void testUpdateMissingAppId() throws Exception {
        //Arrange
        UpdateRequestDto updateRequestDto = new UpdateRequestDto();
        updateRequestDto.setAppId(null);
        updateRequestDto.setVersion("0.0.9");

        //Act and Assert
        mockMvc.perform(post("/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Either AppId or version is missing"));
    }

    @Test
    public void testUpdateMissingVersion() throws Exception {
        //Arrange
        UpdateRequestDto updateRequestDto = new UpdateRequestDto();
        updateRequestDto.setAppId(UUID.randomUUID());
        updateRequestDto.setVersion(null);

        //Act and Assert
        mockMvc.perform(post("/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Either AppId or version is missing"));
    }

    @Test
    public void testCheckUpdatesNoUpdate() throws Exception {
        UUID appId = UUID.randomUUID();
        String installedVersion = "0.0.4";

        // Mock the behavior of the deployService
        when(deployService.getLatestVersion(any(UUID.class), anyString())).thenReturn(null);

        mockMvc.perform(get("/update/{id}/installedVersion/{version}", appId, installedVersion)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.appId").value(appId.toString()))
                .andExpect(jsonPath("$.status").value(CheckUpdateStatus.NO_UPDATE.toString()))
                .andExpect(jsonPath("$.version").isEmpty());
    }

    @Test
    public void testCheckUpdatesAvailableUpdate() throws Exception {
        UUID appId = UUID.randomUUID();
        String installedVersion = "0.0.5";
        Version newVersion = new Version("0.0.6");

        // Mock the behavior of the deployService
        when(deployService.getLatestVersion(any(UUID.class), anyString())).thenReturn(newVersion);

        mockMvc.perform(get("/update/{id}/installedVersion/{version}", appId, installedVersion)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.appId").value(appId.toString()))
                .andExpect(jsonPath("$.status").value(CheckUpdateStatus.UPDATE_AVAILABLE.toString()))
                .andExpect(jsonPath("$.version.value").value("0.0.6"));
    }
}
