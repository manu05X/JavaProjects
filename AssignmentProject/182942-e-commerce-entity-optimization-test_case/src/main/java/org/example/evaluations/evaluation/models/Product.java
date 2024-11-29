package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Data
public class Product extends BaseModel {

    private String name;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product")
    @Fetch(FetchMode.SUBSELECT)
    private List<Image> images;
}
