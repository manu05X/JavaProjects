package com.example.ECommerce.service;

import com.example.ECommerce.dto.GenericProductDto;
import com.example.ECommerce.exceptions.ProductNotFoundException;
import com.example.ECommerce.model.Category;
import com.example.ECommerce.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    GenericProductDto getProductByIdService(Long id) throws ProductNotFoundException;
    List<Product> getProductsLimited(int limit);
    List<Product> getProductsSorted(String sort);
    List<Category>getAllCategories();
    List<Product> getProductByCategory(String category);
    Product addProduct(Product product);
    //Product updateProduct(Long id, FakeStoreProductDTO product);
    Product updateProduct(Long id, Product product);
    //void deleteProduct(Long id);
    public String deleteProduct(Long id);
}
