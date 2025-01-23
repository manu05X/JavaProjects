package com.example.ECommerce.controller;

import com.example.ECommerce.dto.GenericProductDto;
import com.example.ECommerce.exceptions.ProductNotFoundException;
import com.example.ECommerce.mapper.ProductMapper;
import com.example.ECommerce.model.Category;
import com.example.ECommerce.model.Product;
import com.example.ECommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productServiceObj;
    private ProductMapper productMapper;


    /*
    @Autowired
    public ProductController (@Qualifier("SelfProductService") ProductService productService){
        this.productServiceObj = productService;
    }

     */
    //Insted of using @Qualifiers we can make use of @Primary annotation for default use of which service

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productServiceObj = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productServiceObj.getAllProducts();
    }


    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable Long id) throws ProductNotFoundException {
        System.out.println("Calling the Controller in test....");
        GenericProductDto product = productServiceObj.getProductByIdService(id);
        if(product == null){
            return productMapper.mapProductToGenericDTO(new Product());
        }
        return product;
    }

    // http://localhost:8080/products/limited?limit=1
    @GetMapping("/limited")
    public List<Product> getProductsLimited(@RequestParam(defaultValue = "5") int limit) {
        return productServiceObj.getProductsLimited(limit);
    }

    @GetMapping("/sorted")
    public List<Product> getProductsSorted(@RequestParam(defaultValue = "desc") String sort) {
        return productServiceObj.getProductsSorted(sort);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return productServiceObj.getAllCategories();
    }


    @GetMapping("/category/{category}")
    public List<Product> getProductByCategory(@PathVariable String category){
        return productServiceObj.getProductByCategory(category);
    }


    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productServiceObj.addProduct(product);
    }


//    @PutMapping("/{id}")
//    public Product updateProduct(@PathVariable Long id, @RequestBody FakeStoreProductDTO product){
//        return productServiceObj.updateProduct(id,product);
//    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        return productServiceObj.updateProduct(id,product);
    }


//    @DeleteMapping("/{id}")
//    public void deleteProduct(@PathVariable Long id){
//        productServiceObj.deleteProduct(id);
//    }

    // In your controller:
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String result = productServiceObj.deleteProduct(id);
        return ResponseEntity.ok(result);
    }


}
