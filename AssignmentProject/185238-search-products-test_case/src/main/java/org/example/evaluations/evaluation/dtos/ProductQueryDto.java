package org.example.evaluations.evaluation.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductQueryDto {
    private Integer pageSize;
    private Integer pageNumber;
    private String query;
    private String sortParamName;
    private SortType sortType;
}
