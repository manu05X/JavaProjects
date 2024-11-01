package com.example.TurnamentRegistration.service;

import com.example.TurnamentRegistration.entity.Category;
import com.example.TurnamentRegistration.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepos;

    public List<Category> allCategories(){
        return categoryRepos.findAll();
    }

    public Category getCategory(int id){
        Optional<Category> temp = categoryRepos.findById(id);

        Category category = null;

        if(temp.isEmpty()){
            throw new RuntimeException("Player with id {"+ id +"} not found");
        } else {
            category = temp.get();
        }

        return category;
    }


    public Category addCategory(Category category){
        category.setId(0);

        return categoryRepos.save(category);
    }

    public void deleteCategory(int id){
        Optional<Category> temp = categoryRepos.findById(id);

        Category category = null;

        if(temp.isEmpty()){
            throw new RuntimeException("Player with id {"+ id +"} not found");
        } else {
            category = temp.get();
        }

        categoryRepos.save(category);
    }
}
