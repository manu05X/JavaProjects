package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLocationDto {
    double latitude;
    double longitude;
    double radius;
}
