package org.example.evaluations.controllers;

import org.example.evaluations.evaluation.controllers.CategoryController;
import org.example.evaluations.evaluation.models.Product;
import org.example.evaluations.evaluation.services.ICategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
public class CategoryControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryService categoryService;

    @Test
    public void testGetProducts() throws Exception {
        Product product1 = new Product();
        product1.setTitle("Laptop");

        Product product2 = new Product();
        product2.setTitle("Smartphone");

        List<Product> products = Arrays.asList(product1, product2);

        String categoryName = "Electronics";
        when(categoryService.getProducts(categoryName)).thenReturn(products);

        mockMvc.perform(get("/products/category/{categoryName}", categoryName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Laptop"))
                .andExpect(jsonPath("$[1].title").value("Smartphone"));
    }
}
