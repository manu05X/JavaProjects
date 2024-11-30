package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.ProductController;
import org.example.evaluations.evaluation.dtos.ProductRequestDto;
import org.example.evaluations.evaluation.models.Product;
import org.example.evaluations.evaluation.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddProduct() throws Exception {
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setTitle("Test Product");
        productRequestDto.setPrice(29.99);
        productRequestDto.setImageUrl("http://example.com/image.jpg");
        productRequestDto.setDescription("This is a test product.");
        productRequestDto.setCategory("Test Category");

        Product savedProduct = new Product();
        savedProduct.setTitle("Test Product");
        savedProduct.setPrice(29.99);
        savedProduct.setImageUrl("http://example.com/image.jpg");
        savedProduct.setDescription("This is a test product.");
        savedProduct.setCategory("Test Category");

        when(productService.addProduct(any(Product.class))).thenReturn(savedProduct);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Product"))
                .andExpect(jsonPath("$.price").value(29.99))
                .andExpect(jsonPath("$.imageUrl").value("http://example.com/image.jpg"));
    }

    @Test
    void testGetPopularProducts() throws Exception {
        Set<Product> popularProducts = new HashSet<>();

        Product product1 = new Product();
        product1.setTitle("Popular Product 1");
        product1.setPrice(19.99);
        product1.setImageUrl("http://example.com/image1.jpg");

        popularProducts.add(product1);

        when(productService.getPopularProducts()).thenReturn(popularProducts);

        mockMvc.perform(get("/product")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Popular Product 1"));
    }
}
