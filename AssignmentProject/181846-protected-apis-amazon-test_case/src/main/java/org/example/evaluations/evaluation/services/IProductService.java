package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.AmazonProduct;

import java.util.List;

public interface IProductService {
    List<AmazonProduct> getProductByName(String name);
    List<AmazonProduct> getProductByCategoryId(String categoryId);
}
