package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.CategoryController;
import org.example.evaluations.evaluation.models.Category;
import org.example.evaluations.evaluation.services.ICategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @MockBean
    private ICategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddCategory() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        when(categoryService.addCategory(any(Category.class))).thenReturn(category);

        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Electronics"));
    }

    @Test
    void testGetAllPremiumCategories() throws Exception {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Electronics");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Books");

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryService.getAllPremiumCategories()).thenReturn(categories);

        mockMvc.perform(get("/category/premium"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Electronics"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Books"));
    }

    @Test
    void testGetCategoriesBetweenIds() throws Exception {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Electronics");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Books");

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryService.getCategoriesBetweenIds(anyLong(), anyLong())).thenReturn(categories);

        mockMvc.perform(get("/category/1/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Electronics"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Books"));
    }

    @Test
    void testGetCategoriesWithMatchingName() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        List<Category> categories = Arrays.asList(category);

        when(categoryService.getCategoriesWithMatchingName(any(String.class))).thenReturn(categories);

        mockMvc.perform(get("/category/Electronics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Electronics"));
    }
}
