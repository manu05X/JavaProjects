package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.InstallStatus;
import org.example.evaluations.evaluation.models.UpdateStatus;
import org.example.evaluations.evaluation.models.Version;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeployService implements IDeployService{
    @Override
    public InstallStatus install(UUID appId) {
        return InstallStatus.INSTALL_SUCCESS;
    }

    @Override
    public UpdateStatus update(UUID appId, String version) {
        switch (version) {
            case "0.0.1" :
            case "0.0.3" :
            case "0.0.5" :
            case "0.0.7" :
            case "0.0.9" :
                return UpdateStatus.UPDATE_SUCCESS;
            default:
                return UpdateStatus.UPDATE_FAILED;
        }
    }

    @Override
    public Version getLatestVersion(UUID appId, String installedVersion) {
        return Version.getLatestIfAvailable(installedVersion);
    }
}
