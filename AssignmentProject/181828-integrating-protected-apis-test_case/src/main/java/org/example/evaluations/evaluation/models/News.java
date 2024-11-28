package org.example.evaluations.evaluation.models;

import lombok.Data;

@Data
public class News {
    private String article_title;
    private String article_url;
    private String source;
    private String post_time_utc;
}
