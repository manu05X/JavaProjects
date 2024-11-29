package org.example.evaluations.evaluation.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("BlogPost")
public class BlogPost extends Publication {
    private String url;
}
