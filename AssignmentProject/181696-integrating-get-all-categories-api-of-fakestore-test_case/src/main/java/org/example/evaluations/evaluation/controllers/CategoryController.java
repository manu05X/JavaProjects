package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.models.Category;
import org.example.evaluations.evaluation.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public List<Category> getCategories() {
       return categoryService.getCategories();
    }
}
