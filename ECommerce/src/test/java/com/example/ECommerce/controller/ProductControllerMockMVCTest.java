package com.example.ECommerce.controller;

import com.example.ECommerce.dto.GenericProductDto;
import com.example.ECommerce.exceptions.ProductNotFoundException;
import com.example.ECommerce.model.Category;
import com.example.ECommerce.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMockMVCTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ResultActions resultActions;

    /*
    @Test
    public void testProductByIdAPI() throws ProductNotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(1L);
        genericProductDto.setTitle("iPhone");
        genericProductDto.setCategory("electronics");


        when(productService.getProductByIdService(1L)).thenReturn(genericProductDto);

        mockMvc.perform(get("/product/1"))
                .andExpect(status().is(200));
//                .andExpect(content().json("{\"id\":1,\"title\":\"iPhone\",\"description\":null,\"image\":null,\"category\":\"electronics\",\"price\":0.0}"))
//                .andExpect(jsonPath("$.id").value(1L));


        String responseString = resultActions.andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("{\"id\":1,\"title\":\"iPhone\",\"description\":null,\"image\":null,\"category\":\"electronics\",\"price\":0.0}",
                responseString);

        GenericProductDto responseDto = objectMapper.readValue(responseString, GenericProductDto.class);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(1L, responseDto.getId());
    } */


    @Test
    public void testProductByIdAPI() throws Exception {
        // Create a Category object
        Category category = new Category();
        category.setCategory("electronics");

        // Create and populate the GenericProductDto
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(1L);
        genericProductDto.setTitle("iPhone");
        genericProductDto.setCategory(category); // Set the category object

        // Mock the ProductService to return the GenericProductDto
        when(productService.getProductByIdService(1L)).thenReturn(genericProductDto);

        // Perform the GET request and validate the response
        ResultActions resultActions = mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("iPhone"))
                .andExpect(jsonPath("$.category.category").value("electronics")); // Validate nested category field

        // Extract the response content as a string
        String responseString = resultActions.andReturn().getResponse().getContentAsString();

        // Deserialize the response string into a GenericProductDto object
        GenericProductDto responseDto = objectMapper.readValue(responseString, GenericProductDto.class);

        // Assertions to verify the response content
        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(1L, responseDto.getId());
        Assertions.assertEquals("iPhone", responseDto.getTitle());
        Assertions.assertEquals("electronics", responseDto.getCategory().getCategory());
    }


}
