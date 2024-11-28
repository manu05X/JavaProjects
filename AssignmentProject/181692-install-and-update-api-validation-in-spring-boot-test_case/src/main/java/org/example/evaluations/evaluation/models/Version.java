package org.example.evaluations.evaluation.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Version {
    private String value;
    private VersionType type;

    public Version() {
        value = "0.0.1";
        type = VersionType.MAJOR;
    }

    public Version(String value) {
        this.value = value;
        this.type = VersionType.MINOR;
    }

    public static Version getLatestIfAvailable(String installedVersion) {
        String[] parts = installedVersion.split("\\.");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid version string format.");
        }

        try {
            int major = Integer.parseInt(parts[0]);
            int minor = Integer.parseInt(parts[1]);
            int patch = Integer.parseInt(parts[2]);
            int version = major + minor + patch;

            if(version == 9) {
                return null;
            }
            version++;
            String newVersion = "0.0."+version;
            return new Version(newVersion);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Version string contains non-numeric values.");
        }
    }
}
