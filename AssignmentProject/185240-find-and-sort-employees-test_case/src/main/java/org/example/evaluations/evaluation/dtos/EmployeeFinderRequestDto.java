package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class EmployeeFinderRequestDto {
    private String department;
    private Integer pageNumber;
    private Integer pageSize;
    List<SortParam> sortParamList = new ArrayList<>();
}
