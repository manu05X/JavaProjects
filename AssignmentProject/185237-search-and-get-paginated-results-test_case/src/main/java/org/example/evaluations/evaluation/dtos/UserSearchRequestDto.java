package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSearchRequestDto {
    private String query;
    private Integer pageNumber;
}
