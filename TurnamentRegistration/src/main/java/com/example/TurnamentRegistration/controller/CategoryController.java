package com.example.TurnamentRegistration.controller;


import com.example.TurnamentRegistration.entity.Category;
import com.example.TurnamentRegistration.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAllCategory(){
        return categoryService.allCategories();
    }

    @GetMapping("/categories/{id}")
    public Category getCategory(@PathVariable int id){
        return categoryService.getCategory(id);
    }

    @PostMapping("/categories")
    public Category addCategory(@RequestBody Category category){
        category.setId(0);

        return categoryService.addCategory(category);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
    }
}
