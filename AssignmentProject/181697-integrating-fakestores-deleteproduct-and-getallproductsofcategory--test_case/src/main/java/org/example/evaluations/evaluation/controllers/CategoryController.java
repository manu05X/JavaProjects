package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.models.Product;
import org.example.evaluations.evaluation.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/{categoryName}")
    public List<Product> getProducts(@PathVariable String categoryName) {
       return categoryService.getProducts(categoryName);
    }
}
