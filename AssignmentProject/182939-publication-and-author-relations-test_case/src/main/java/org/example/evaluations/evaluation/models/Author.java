package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Author {
    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "authors")
    private List<Publication> publications;
}
