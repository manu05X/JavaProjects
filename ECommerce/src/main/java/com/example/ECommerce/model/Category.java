package com.example.ECommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Category extends BaseModel{
    private String category;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;

    // No-argument constructor
    public Category() {}

    // Constructor with the category name
    public Category(String category) {
        this.category = category;
    }

    // Getter and Setter
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

