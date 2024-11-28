package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FakeStoreCategoryService implements ICategoryService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        RestTemplate restTemplate = restTemplateBuilder.build();
        String[] fakeStoreCategories =  restTemplate.getForEntity("https://fakestoreapi.com/products/categories",String[].class).getBody();
        for(String fsCategory : fakeStoreCategories) {
          categories.add(from(fsCategory));
        }
        return categories;
    }

    private Category from(String fakeStoreCategory) {
        Category category = new Category();
        category.setName(fakeStoreCategory);
        category.setId(new Random().nextLong());
        return category;
    }
}
