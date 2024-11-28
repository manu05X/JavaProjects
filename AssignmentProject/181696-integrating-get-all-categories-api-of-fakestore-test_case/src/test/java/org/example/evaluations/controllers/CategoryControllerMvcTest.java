package org.example.evaluations.controllers;

import org.example.evaluations.evaluation.controllers.CategoryController;
import org.example.evaluations.evaluation.models.Category;
import org.example.evaluations.evaluation.services.ICategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
public class CategoryControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryService categoryService;

    @Test
    public void testGetCategories() throws Exception {
        Category category1 = new Category();
        category1.setName("Electronics");
        category1.setId(1L);

        Category category2 = new Category();
        category2.setName("Books");
        category2.setId(2L);

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryService.getCategories()).thenReturn(categories);

        mockMvc.perform(get("/products/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Electronics"))
                .andExpect(jsonPath("$[1].name").value(("Books")));
    }
}
