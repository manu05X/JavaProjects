package org.example.evaluations.controllers;

import org.example.evaluations.evaluation.controllers.ProductController;
import org.example.evaluations.evaluation.dtos.ProductQueryDto;
import org.example.evaluations.evaluation.models.Product;
import org.example.evaluations.evaluation.services.ProductSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.example.evaluations.evaluation.dtos.SortType;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductSearchService productSearchService;


    @Test
    void testFindProductsWhichCanBeTakenInHandBaggageBecauseOfWeightLimit() throws Exception {
        // Arrange
        Double actualWeight = 5.0;
        Double weightLimit = 10.0;
        Slice<Product> productSlice = new SliceImpl<>(Collections.singletonList(new Product()));

        when(productSearchService.findProductsWhichCanBeTakenInHandBaggageBecauseOfWeightLimit(weightLimit, actualWeight, 0, 10))
                .thenReturn(productSlice);

        // Act & Assert
        mockMvc.perform(get("/products/{actualWeight}/{weightLimit}", actualWeight, weightLimit)
                        .param("pageNumber", "0")
                        .param("pageSize", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testFindProductsBelongingToCategoryDisplayedByAge() throws Exception {
        // Arrange
        String category = "electronics";
        Slice<Product> productSlice = new SliceImpl<>(Collections.singletonList(new Product()));

        when(productSearchService.findProductsBelongingToCategoryShownByAge(category, 0, 10))
                .thenReturn(productSlice);

        // Act & Assert
        mockMvc.perform(get("/products/category/{category}", category)
                        .param("pageNumber", "0")
                        .param("pageSize", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testFindProductsBySearchQuerySortedByUserProvidedCriteria() throws Exception {
        // Arrange
        ProductQueryDto productQueryDto = new ProductQueryDto();
        productQueryDto.setQuery("test");
        productQueryDto.setPageNumber(0);
        productQueryDto.setPageSize(10);
        productQueryDto.setSortParamName("name");
        productQueryDto.setSortType(SortType.ASC);

        Page<Product> productPage = new PageImpl<>(Collections.singletonList(new Product()));

        when(productSearchService.findProductsBySearchQuerySortedByUserProvidedCriteria(
                productQueryDto.getQuery(), productQueryDto.getPageNumber(),
                productQueryDto.getPageSize(), productQueryDto.getSortParamName(), productQueryDto.getSortType()))
                .thenReturn(productPage);

        // Act & Assert
        mockMvc.perform(post("/products/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"query\":\"test\",\"pageNumber\":0,\"pageSize\":10,\"sortParamName\":\"name\",\"sortType\":\"ASC\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
