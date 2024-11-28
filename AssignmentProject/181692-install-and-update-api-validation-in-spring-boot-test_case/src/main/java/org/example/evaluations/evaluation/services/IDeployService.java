package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.InstallStatus;
import org.example.evaluations.evaluation.models.UpdateStatus;
import org.example.evaluations.evaluation.models.Version;

import java.util.UUID;

public interface IDeployService {
    InstallStatus install(UUID appId);

    UpdateStatus update(UUID appId, String version);

    Version getLatestVersion(UUID appId, String installedVersion);
}
