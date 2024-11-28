package org.example.evaluations.services;

import org.example.evaluations.evaluation.models.Category;
import org.example.evaluations.evaluation.services.FakeStoreCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FakeStoreCategoryServiceTest {

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreCategoryService fakeStoreCategoryService;

    @Test
    public void testGetCategories() {
        RestTemplate mockRestTemplate = mock(RestTemplate.class);
        when(restTemplateBuilder.build()).thenReturn(mockRestTemplate);

        String[] fakeStoreCategories = {"Electronics", "Books", "Clothing"};
        when(mockRestTemplate.getForEntity("https://fakestoreapi.com/products/categories", String[].class)).thenReturn(
                new org.springframework.http.ResponseEntity<>(fakeStoreCategories, org.springframework.http.HttpStatus.OK)
        );

        List<Category> categories = fakeStoreCategoryService.getCategories();


        assertEquals(3, categories.size(), "You need to use url https://fakestoreapi.com/products/categories");
        assertEquals("Electronics", categories.get(0).getName(),"You need to use url https://fakestoreapi.com/products/categories");
        assertEquals("Books", categories.get(1).getName(),"You need to use url https://fakestoreapi.com/products/categories");
        assertEquals("Clothing", categories.get(2).getName(),"You need to use url https://fakestoreapi.com/products/categories");
        assertNotNull(categories.get(0).getId(),"Please generate random long number and assign it to Id, rather than keeping it null.");
        assertNotNull(categories.get(1).getId(),"Please generate random long number and assign it to Id, rather than keeping it null.");
        assertNotNull(categories.get(2).getId(),"Please generate random long number and assign it to Id, rather than keeping it null.");
    }
}
