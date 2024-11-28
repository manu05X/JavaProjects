package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.AmazonProductDataDto;
import org.example.evaluations.evaluation.dtos.AmazonProductDto;
import org.example.evaluations.evaluation.models.AmazonProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AmazonProductService implements IProductService {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    private static final String key = "x-rapidapi-key";
    private static final String value = "650b8aea0cmshc580a2cefb4df87p172f51jsnc0e061860c02";

    public List<AmazonProduct> getProductByName(String name) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.set(key,value);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        AmazonProductDto amazonProductDto = restTemplate.exchange("https://real-time-amazon-data.p.rapidapi.com/search?query={name}", HttpMethod.GET,entity, AmazonProductDto.class,name).getBody();
        AmazonProductDataDto amazonProductDataDto = amazonProductDto.getData();
        return amazonProductDataDto.getProducts();
    }

    public List<AmazonProduct> getProductByCategoryId(String categoryId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.set(key,value);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        AmazonProductDto amazonProductDto = restTemplate.exchange("https://real-time-amazon-data.p.rapidapi.com/products-by-category?category_id={cid}", HttpMethod.GET,entity, AmazonProductDto.class,categoryId).getBody();
        AmazonProductDataDto amazonProductDataDto = amazonProductDto.getData();
        return amazonProductDataDto.getProducts();
    }
}
