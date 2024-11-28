package org.example.evaluations.services;

import org.example.evaluations.evaluation.dtos.FakeStoreProductDto;
import org.example.evaluations.evaluation.models.Product;
import org.example.evaluations.evaluation.services.FakeStoreCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FakeStoreCategoryServiceTest {

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private FakeStoreCategoryService fakeStoreCategoryService;

    @BeforeEach
    public void setUp() {
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }

    @Test
    public void testGetProducts() {
        FakeStoreProductDto dto1 = new FakeStoreProductDto();
        dto1.setCategory("Electronics");
        dto1.setTitle("Laptop");
        dto1.setPrice(999.99);

        FakeStoreProductDto dto2 = new FakeStoreProductDto();
        dto2.setCategory("Electronics");
        dto2.setTitle("Smartphone");
        dto2.setPrice(499.99);

        FakeStoreProductDto[] fakeStoreProductDtos = {dto1, dto2};
        String categoryName = "Electronics";

        when(restTemplate.getForEntity(eq("https://fakestoreapi.com/products/category/{categoryName}"), eq(FakeStoreProductDto[].class), eq(categoryName)))
                .thenReturn(new org.springframework.http.ResponseEntity<>(fakeStoreProductDtos, org.springframework.http.HttpStatus.OK));

        List<Product> products = fakeStoreCategoryService.getProducts(categoryName);

        assertEquals(2, products.size());
        assertEquals("Laptop", products.get(0).getTitle());
        assertEquals(999.99, products.get(0).getPrice());
        assertEquals("Smartphone", products.get(1).getTitle());
        assertEquals(499.99, products.get(1).getPrice());
    }
}
