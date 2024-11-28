package org.example.evaluations.evaluation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public void deleteProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{productId}",productId);
    }
}
