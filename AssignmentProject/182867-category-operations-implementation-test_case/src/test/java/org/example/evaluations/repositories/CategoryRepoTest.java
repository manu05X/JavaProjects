package org.example.evaluations.repositories;

import org.example.evaluations.evaluation.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.example.evaluations.evaluation.repos.CategoryRepo;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    void testSaveCategory() {
        Category category = new Category();
        category.setName("Electronics");
        category.setIsPremium(true);
        category.setId(1L);

        Category savedCategory = categoryRepo.save(category);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isNotNull();
        assertThat(savedCategory.getName()).isEqualTo("Electronics");
    }

    @Test
    void testFindCategoryById() {
        Category category = new Category();
        category.setName("Books");
        category.setId(2L);
        category.setIsPremium(false);
        Category savedCategory = categoryRepo.save(category);

        Optional<Category> foundCategory = categoryRepo.findCategoryById(savedCategory.getId());

        assertThat(foundCategory).isPresent();
        assertThat(foundCategory.get().getName()).isEqualTo("Books");
    }

    @Test
    void testFindCategoryByName() {
        Category category = new Category();
        category.setName("Toys");
        category.setId(11L);
        category.setIsPremium(true);
        categoryRepo.save(category);

        List<Category> categories = categoryRepo.findCategoryByName("Toys");

        assertThat(categories).isNotEmpty();
        assertThat(categories.get(0).getName()).isEqualTo("Toys");
    }

    @Test
    void testFindCategoryByIdBetween() {
        Category category1 = new Category();
        category1.setName("Sports");
        category1.setId(100L);
        category1.setIsPremium(true);
        Category savedCategory1 = categoryRepo.save(category1);

        Category category2 = new Category();
        category2.setName("Outdoors");
        category2.setIsPremium(true);
        category2.setId(150L);
        Category savedCategory2 = categoryRepo.save(category2);

        List<Category> categories = categoryRepo.findCategoryByIdBetween(savedCategory1.getId(), savedCategory2.getId());

        assertThat(categories).containsExactlyInAnyOrder(category1, category2);
    }

    @Test
    void testFindCategoryByIsPremiumTrue() {
        Category category1 = new Category();
        category1.setName("Luxury");
        category1.setIsPremium(true);
        category1.setId(999L);
        categoryRepo.save(category1);

        Category category2 = new Category();
        category2.setName("Affordable");
        category2.setIsPremium(false);
        category2.setId(59L);
        categoryRepo.save(category2);

        List<Category> premiumCategories = categoryRepo.findCategoryByIsPremiumTrue();

        assertThat(premiumCategories).hasSize(1);
        assertThat(premiumCategories.get(0).getName()).isEqualTo("Luxury");
    }
}
