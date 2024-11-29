package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Category;
import org.example.evaluations.evaluation.models.Image;
import org.example.evaluations.evaluation.models.Product;
import org.example.evaluations.evaluation.models.SubCategory;
import org.example.evaluations.evaluation.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<String> getNamesOfAllCategoriesAndLinkedProductsAndTheirImages() {
        List<String> namesOfAllEntities = new ArrayList<>();

        List<Category> categories = categoryRepo.findAll();
        for(Category category : categories) {
            namesOfAllEntities.add(category.getTitle());
            for(Product product : category.getProductList()) {
                namesOfAllEntities.add(product.getName());
                for(Image image : product.getImages()) {
                     namesOfAllEntities.add(image.getDescriptiveName());
                }
            }
        }

        return namesOfAllEntities;
    }

    public List<String> getNamesOfAllCategoriesAndTheirSubCategories() {
        List<String> namesOfAllEntities = new ArrayList<>();

        List<Category> categories = categoryRepo.findAll();
        for(Category category : categories) {
            namesOfAllEntities.add(category.getTitle());
            for(SubCategory subCategory : category.getSubCategories()) {
                namesOfAllEntities.add(subCategory.getName());
            }
        }

        return namesOfAllEntities;
    }
}
