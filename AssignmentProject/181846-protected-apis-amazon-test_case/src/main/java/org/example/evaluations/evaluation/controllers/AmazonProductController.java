package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.models.AmazonProduct;
import org.example.evaluations.evaluation.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/amazon")
public class AmazonProductController {

    @Autowired
    private IProductService amazonProductService;

    //localhost:8080/amazon/search?query=Phone
    @GetMapping("/search")
    public List<AmazonProduct> searchProductByName(@RequestParam(required = true) String query) {
        return amazonProductService.getProductByName(query);
    }

    //localhost:8080/amazon/products-by-category?categoryid=2478868012
    @GetMapping("/products-by-category")
    public List<AmazonProduct> getProductsByCategoryId(@RequestParam(value = "categoryid",required = true) String categoryId) {
        return amazonProductService.getProductByCategoryId(categoryId);
    }
}
