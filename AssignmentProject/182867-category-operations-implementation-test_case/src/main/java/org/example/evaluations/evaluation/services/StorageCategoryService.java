package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Category;
import org.example.evaluations.evaluation.repos.CategoryRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageCategoryService implements ICategoryService {

    @Autowired
    private CategoryRepo categoryRepository;

    public Category addCategory(@NotNull Category category) {
        Optional<Category> categoryOptional = categoryRepository.findCategoryById(category.getId());
        if(categoryOptional.isPresent()) {
            return categoryOptional.get();
        }

        return categoryRepository.save(category);
    }

    public List<Category> getAllPremiumCategories() {
        return categoryRepository.findCategoryByIsPremiumTrue();
    }

    public List<Category> getCategoriesBetweenIds(Long categoryId1, Long categoryId2) {
        return categoryRepository.findCategoryByIdBetween(categoryId1,categoryId2);
    }


    public List<Category> getCategoriesWithMatchingName(String categoryName) {
        return categoryRepository.findCategoryByName(categoryName);
    }
}
