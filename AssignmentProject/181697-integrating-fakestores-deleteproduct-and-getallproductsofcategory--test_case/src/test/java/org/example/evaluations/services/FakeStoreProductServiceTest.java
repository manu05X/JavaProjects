package org.example.evaluations.services;

import org.example.evaluations.evaluation.services.FakeStoreProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FakeStoreProductServiceTest {

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private FakeStoreProductService fakeStoreProductService;

    @BeforeEach
    public void setUp() {
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }

    @Test
    public void testDeleteProduct() {
        Long productId = 1L;

        doNothing().when(restTemplate).delete(eq("https://fakestoreapi.com/products/{productId}"), eq(productId));

        fakeStoreProductService.deleteProduct(productId);

        verify(restTemplate).delete(eq("https://fakestoreapi.com/products/{productId}"), eq(productId));
    }
}
