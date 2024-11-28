package org.example.evaluations.evaluation.dtos;

import lombok.Data;
import org.example.evaluations.evaluation.models.News;

import java.util.List;

@Data
public class RealTimeNewsData {
    private List<News> news;
}
