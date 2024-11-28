package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.CheckUpdateResponseDto;
import org.example.evaluations.evaluation.dtos.UpdateRequestDto;
import org.example.evaluations.evaluation.dtos.UpdateResponseDto;
import org.example.evaluations.evaluation.models.CheckUpdateStatus;
import org.example.evaluations.evaluation.models.UpdateStatus;
import org.example.evaluations.evaluation.models.Version;
import org.example.evaluations.evaluation.services.IDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/update")
public class UpdateController {

    @Autowired
    private IDeployService deployService;

    @PostMapping
    public UpdateResponseDto update(@RequestBody UpdateRequestDto updateRequestDto) {
        validatePayload(updateRequestDto.getAppId(),updateRequestDto.getVersion());
        UUID appId = updateRequestDto.getAppId();
        String version = updateRequestDto.getVersion();
        UpdateStatus updateStatus = deployService.update(appId,version);
        UpdateResponseDto updateResponseDto = new UpdateResponseDto();
        updateResponseDto.setStatus(updateStatus);
        updateResponseDto.setAppId(appId);
        updateResponseDto.setVersion(new Version(version));
        return updateResponseDto;
    }

    @GetMapping("/{id}/installedVersion/{version}")
    public CheckUpdateResponseDto checkUpdates(@PathVariable("id") UUID  appId, @PathVariable("version") String installedVersion) {
        CheckUpdateResponseDto checkUpdateResponseDto = new CheckUpdateResponseDto();
        Version newVersion = deployService.getLatestVersion(appId,installedVersion);

        if(newVersion== null) {
            checkUpdateResponseDto.setStatus(CheckUpdateStatus.NO_UPDATE);
            checkUpdateResponseDto.setVersion(null);
            checkUpdateResponseDto.setAppId(appId);
            return checkUpdateResponseDto;
        }else {
            checkUpdateResponseDto.setStatus(CheckUpdateStatus.UPDATE_AVAILABLE);
            checkUpdateResponseDto.setAppId(appId);
            checkUpdateResponseDto.setVersion(newVersion);
            return checkUpdateResponseDto;
        }
    }

    private void validatePayload(UUID appId,String version) {
        if(appId == null || version == null) {
            throw new IllegalArgumentException("Either AppId or version is missing");
        }
    }
}
