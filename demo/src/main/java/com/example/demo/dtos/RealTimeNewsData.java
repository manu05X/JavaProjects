package com.example.demo.dtos;

import com.example.demo.models.News;
import lombok.Data;

import java.util.List;

@Data
public class RealTimeNewsData {
    private List<News> news;
}
