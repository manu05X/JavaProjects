package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.evaluations.evaluation.models.CheckUpdateStatus;
import org.example.evaluations.evaluation.models.Version;

import java.util.UUID;

@Setter
@Getter
public class CheckUpdateResponseDto {
    private UUID appId;
    private CheckUpdateStatus status;
    private Version version;
}
