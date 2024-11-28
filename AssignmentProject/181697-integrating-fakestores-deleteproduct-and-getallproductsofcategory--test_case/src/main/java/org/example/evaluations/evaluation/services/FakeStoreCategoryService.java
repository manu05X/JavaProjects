package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.FakeStoreProductDto;
import org.example.evaluations.evaluation.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCategoryService implements ICategoryService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public List<Product> getProducts(String categoryName) {
        List<Product> products = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForEntity("https://fakestoreapi.com/products/category/{categoryName}", FakeStoreProductDto[].class,categoryName).getBody();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
             products.add(from(fakeStoreProductDto));
        }

        return products;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        return product;
    }
}
