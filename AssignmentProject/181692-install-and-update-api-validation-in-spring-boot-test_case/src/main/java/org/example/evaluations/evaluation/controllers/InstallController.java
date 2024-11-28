package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.InstallRequestDto;
import org.example.evaluations.evaluation.dtos.InstallResponseDto;
import org.example.evaluations.evaluation.models.InstallStatus;
import org.example.evaluations.evaluation.services.IDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/install")
public class InstallController {

    @Autowired
    private IDeployService deployService;

    @PostMapping
    public InstallResponseDto install(@RequestBody InstallRequestDto installRequestDto) {
        if(installRequestDto.getAppId() == null) {
            throw new IllegalArgumentException("AppId is null");
        }

        UUID appId = installRequestDto.getAppId();
        InstallStatus installStatus = deployService.install(appId);
        InstallResponseDto installResponseDto = new InstallResponseDto();
        installResponseDto.setStatus(installStatus);
        installResponseDto.setAppId(appId);
        return  installResponseDto;
    }
}
