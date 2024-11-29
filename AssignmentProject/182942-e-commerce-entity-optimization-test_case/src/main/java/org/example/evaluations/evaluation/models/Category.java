package org.example.evaluations.evaluation.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Data
public class Category extends BaseModel {

    private String title;

    @OneToMany(mappedBy = "category")
    @Fetch(FetchMode.SUBSELECT)
    private List<Product> productList;

    @OneToMany(mappedBy = "category")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size=3)
    private List<SubCategory> subCategories;
}
