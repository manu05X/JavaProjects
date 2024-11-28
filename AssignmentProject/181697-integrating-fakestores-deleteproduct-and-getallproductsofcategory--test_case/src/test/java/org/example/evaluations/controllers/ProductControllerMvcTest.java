package org.example.evaluations.controllers;

import org.example.evaluations.evaluation.controllers.ProductController;
import org.example.evaluations.evaluation.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Test
    public void testDeleteProduct() throws Exception {
        Long productId = 1L;

        doNothing().when(productService).deleteProduct(productId);

        mockMvc.perform(delete("/products/{productId}", productId))
                .andExpect(status().isOk());

        verify(productService).deleteProduct(productId);
    }
}
