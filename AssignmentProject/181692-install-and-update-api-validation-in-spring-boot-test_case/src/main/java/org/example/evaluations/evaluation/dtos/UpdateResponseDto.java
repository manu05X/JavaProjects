package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.evaluations.evaluation.models.UpdateStatus;
import org.example.evaluations.evaluation.models.Version;

import java.util.UUID;

@Setter
@Getter
public class UpdateResponseDto {
    private UpdateStatus status;
    private Version version;
    private UUID appId;
}
