package com.example.ECommerce.service.impl;

import com.example.ECommerce.dto.GenericProductDto;
import com.example.ECommerce.exceptions.ProductNotFoundException;
import com.example.ECommerce.model.Category;
import com.example.ECommerce.model.Product;
import com.example.ECommerce.repositories.ProductRepository;
import com.example.ECommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public SelfProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts(){
        System.out.println("Calling from self product service");
        return null;
    }


    @Override
    public GenericProductDto getProductByIdService(Long id) throws ProductNotFoundException{
        System.out.println("Calling from self product service");
        return null;
    }

    @Override
    public List<Product> getProductsLimited(int limit){
        System.out.println("Calling from self product service");
        return null;
    }

    @Override
    public List<Product> getProductsSorted(String sort){
        System.out.println("Calling from self product service");
        return null;
    }

    @Override
    public List<Category>getAllCategories(){
        System.out.println("Calling from self product service");
        return null;
    }

    @Override
    public List<Product> getProductByCategory(String category){
        System.out.println("Calling from self product service");
        return null;

    }


    @Override
    public Product addProduct(Product product){
        System.out.println("Calling from self product service");
        return null;

    }
    //Product updateProduct(Long id, FakeStoreProductDTO product);

    @Override
    public Product updateProduct(Long id, Product product){
        System.out.println("Calling from self product service");
        return null;

    }
    //void deleteProduct(Long id);

    @Override
    public String deleteProduct(Long id){
        System.out.println("Calling from self product service");
        return null;

    }
}
