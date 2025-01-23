package com.example.ECommerce.thirdPartyClient.FakeStoreDto;

import com.example.ECommerce.model.Product;

import java.util.List;

public class FakeStoreCategoryDTO {
    private String category;

    private List<Product> products;

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
