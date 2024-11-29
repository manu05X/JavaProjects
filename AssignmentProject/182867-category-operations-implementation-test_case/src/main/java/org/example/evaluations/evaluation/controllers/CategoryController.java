package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.models.Category;
import org.example.evaluations.evaluation.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping
    Category addCategory(@RequestBody Category category) {
      return categoryService.addCategory(category);
    }

    @GetMapping("/premium")
    List<Category> getAllPremiumCategories() {
       return categoryService.getAllPremiumCategories();
    }

    @GetMapping("/{categoryId1}/{categoryId2}")
    List<Category> getCategoriesBetweenIds(@PathVariable Long categoryId1, @PathVariable Long categoryId2) {
        return categoryService.getCategoriesBetweenIds(categoryId1,categoryId2);
    }

    @GetMapping("/{categoryName}")
    List<Category> getCategoriesWithMatchingName(@PathVariable String categoryName) {
         return categoryService.getCategoriesWithMatchingName(categoryName);
    }
}
