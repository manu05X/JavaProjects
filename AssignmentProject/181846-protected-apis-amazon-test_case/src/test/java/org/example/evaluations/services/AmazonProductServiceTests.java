package org.example.evaluations.services;

import org.example.evaluations.evaluation.dtos.AmazonProductDataDto;
import org.example.evaluations.evaluation.dtos.AmazonProductDto;
import org.example.evaluations.evaluation.models.AmazonProduct;
import org.example.evaluations.evaluation.services.AmazonProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class AmazonProductServiceTests {

    @Autowired
    private AmazonProductService amazonProductService;

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }

    @Test
    void testGetProductByName() {
        // Prepare test data
        AmazonProduct amazonProduct = new AmazonProduct();
        List<AmazonProduct> products = Collections.singletonList(amazonProduct);

        AmazonProductDataDto amazonProductDataDto = new AmazonProductDataDto();
        amazonProductDataDto.setProducts(products);

        AmazonProductDto amazonProductDto = new AmazonProductDto();
        amazonProductDto.setData(amazonProductDataDto);

        ResponseEntity<AmazonProductDto> responseEntity = new ResponseEntity<>(amazonProductDto, HttpStatus.OK);
        when(restTemplate.exchange(
                eq("https://real-time-amazon-data.p.rapidapi.com/search?query={name}"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(AmazonProductDto.class),
                any(String.class))
        ).thenReturn(responseEntity);

        // Execute the method under test
        List<AmazonProduct> result = amazonProductService.getProductByName("testProductName");

        // Verify the result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(amazonProduct, result.get(0));
    }

    @Test
    void testGetProductByCategoryId() {
        // Prepare test data
        AmazonProduct amazonProduct = new AmazonProduct();
        amazonProduct.setProduct_photo("photo");
        amazonProduct.setProduct_title("title");
        amazonProduct.setAsin("asin");
        List<AmazonProduct> products = Collections.singletonList(amazonProduct);

        AmazonProductDataDto amazonProductDataDto = new AmazonProductDataDto();
        amazonProductDataDto.setProducts(products);

        AmazonProductDto amazonProductDto = new AmazonProductDto();
        amazonProductDto.setData(amazonProductDataDto);

        ResponseEntity<AmazonProductDto> responseEntity = new ResponseEntity<>(amazonProductDto, HttpStatus.OK);
        when(restTemplate.exchange(
                eq("https://real-time-amazon-data.p.rapidapi.com/products-by-category?category_id={cid}"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(AmazonProductDto.class),
                any(String.class))
        ).thenReturn(responseEntity);

        // Execute the method under test
        List<AmazonProduct> result = amazonProductService.getProductByCategoryId("testCategoryId");

        // Verify the result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(amazonProduct, result.get(0));
    }
}
