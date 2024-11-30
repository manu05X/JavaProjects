package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.SortType;
import org.example.evaluations.evaluation.models.Product;
import org.example.evaluations.evaluation.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductSearchService {

    @Autowired
    private ProductRepository productRepository;

    public Slice<Product> findProductsWhichCanBeTakenInHandBaggageBecauseOfWeightLimit(Double allowedLimit, Double actualWeight, Integer pageNumber, Integer pageSize) {
        Double difference = actualWeight - allowedLimit;
        Sort sort = Sort.by("amount").descending().and(Sort.by("weight"));
        return productRepository.findProductByWeightLessThanEqual(Math.abs(difference), PageRequest.of(pageNumber,pageSize,sort));
    }


    public Slice<Product> findProductsBelongingToCategoryShownByAge(String category, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("age");
        return productRepository.findProductByCategory(category,PageRequest.of(pageNumber,pageSize,sort));
    }

    public Page<Product> findProductsBySearchQuerySortedByUserProvidedCriteria(String query, Integer pageNumber, Integer pageSize, String sortParamName, SortType sortType) {
        Sort sort = null;
        if(sortType.equals(SortType.ASC))
            sort = Sort.by(sortParamName);
        else
            sort = Sort.by(sortParamName).descending();

        return  productRepository.findProductByName(query,PageRequest.of(pageNumber,pageSize,sort));
    }
}
