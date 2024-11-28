package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Product;

import java.util.List;

public interface ICategoryService {
    List<Product> getProducts(String categoryName);
}
