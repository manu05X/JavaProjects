package org.example.evaluations.services;

import org.example.evaluations.evaluation.models.Category;
import org.example.evaluations.evaluation.services.StorageCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.example.evaluations.evaluation.repos.CategoryRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StorageCategoryServiceTest {

    @Autowired
    private StorageCategoryService storageCategoryService;

    @MockBean
    private CategoryRepo categoryRepo;

    @Test
    void testAddCategoryWhenNotExists() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        when(categoryRepo.findCategoryById(anyLong())).thenReturn(Optional.empty());
        when(categoryRepo.save(any(Category.class))).thenReturn(category);

        Category result = storageCategoryService.addCategory(category);

        verify(categoryRepo, times(1)).findCategoryById(anyLong());
        verify(categoryRepo, times(1)).save(category);
        assert(result.equals(category));
    }

    @Test
    void testAddCategoryWhenExists() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        when(categoryRepo.findCategoryById(anyLong())).thenReturn(Optional.of(category));

        Category result = storageCategoryService.addCategory(category);

        verify(categoryRepo, times(1)).findCategoryById(anyLong());
        verify(categoryRepo, times(0)).save(any(Category.class));
        assert(result.equals(category));
    }

    @Test
    void testGetAllPremiumCategories() {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Electronics");
        category1.setIsPremium(true);

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Books");
        category2.setIsPremium(true);

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryRepo.findCategoryByIsPremiumTrue()).thenReturn(categories);

        List<Category> result = storageCategoryService.getAllPremiumCategories();

        verify(categoryRepo, times(1)).findCategoryByIsPremiumTrue();
        assert(result.equals(categories));
    }

    @Test
    void testGetCategoriesBetweenIds() {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Electronics");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Books");

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryRepo.findCategoryByIdBetween(anyLong(), anyLong())).thenReturn(categories);

        List<Category> result = storageCategoryService.getCategoriesBetweenIds(1L, 2L);

        verify(categoryRepo, times(1)).findCategoryByIdBetween(anyLong(), anyLong());
        assert(result.equals(categories));
    }

    @Test
    void testGetCategoriesWithMatchingName() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        List<Category> categories = Arrays.asList(category);

        when(categoryRepo.findCategoryByName(anyString())).thenReturn(categories);

        List<Category> result = storageCategoryService.getCategoriesWithMatchingName("Electronics");

        verify(categoryRepo, times(1)).findCategoryByName(anyString());
        assert(result.equals(categories));
    }
}
