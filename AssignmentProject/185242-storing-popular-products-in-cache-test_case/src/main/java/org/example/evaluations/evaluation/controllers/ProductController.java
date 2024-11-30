package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.ProductRequestDto;
import org.example.evaluations.evaluation.models.Product;
import org.example.evaluations.evaluation.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product addProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = getProduct(productRequestDto);
        return productService.addProduct(product);
    }


    @GetMapping
    public Set<Product> getPopularProducts() {
        return productService.getPopularProducts();
    }

    private Product getProduct(ProductRequestDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        return product;
    }

}
