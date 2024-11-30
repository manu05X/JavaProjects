package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.ProductQueryDto;
import org.example.evaluations.evaluation.models.Product;
import org.example.evaluations.evaluation.services.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {

    @Autowired
    private ProductSearchService productSearchService;

    @GetMapping("/products/{actualWeight}/{weightLimit}")
    public Slice<Product> findProductsWhichCanBeTakenInHandBaggageBecauseOfWeightLimit(@RequestParam Integer pageNumber, @RequestParam Integer pageSize, @PathVariable("actualWeight") Double actualWeight, @PathVariable("weightLimit") Double weightLimit) {
        return productSearchService.findProductsWhichCanBeTakenInHandBaggageBecauseOfWeightLimit(weightLimit,actualWeight,pageNumber,pageSize);
    }


    @GetMapping("/products/category/{category}")
    public Slice<Product> findProductsBelongingToCategoryDisplayedByAge(@RequestParam Integer pageNumber, @RequestParam Integer pageSize, @PathVariable String category) {
        return productSearchService.findProductsBelongingToCategoryShownByAge(category,pageNumber,pageSize);
    }

    @PostMapping("/products/search")
    public Page<Product> findProductsBySearchQuerySortedByUserProvidedCriteria(@RequestBody ProductQueryDto productQueryDto) {
        return productSearchService.findProductsBySearchQuerySortedByUserProvidedCriteria(productQueryDto.getQuery(), productQueryDto.getPageNumber(), productQueryDto.getPageSize(), productQueryDto.getSortParamName(), productQueryDto.getSortType());
    }
}
