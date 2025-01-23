package com.example.ECommerce.thirdPartyClient;


import com.example.ECommerce.exceptions.ProductNotFoundException;
import com.example.ECommerce.mapper.ProductMapper;
import com.example.ECommerce.model.Category;
import com.example.ECommerce.model.Product;
import com.example.ECommerce.thirdPartyClient.FakeStoreDto.FakeStoreProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@Component
public class FakeStoreProductClient {

    //private final RestTemplateBuilder restTemplateBuilder;
    private final RestTemplate restTemplate; // RestTemplate at the class level
    private final ProductMapper productMapper; // Injected Mapper
    // private final String fakeStoreUrl = "https://fakestoreapi.com/products";


    @Value("${product.fakestore.api.url}")
    private String fakeStoreUrl;

    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder, ProductMapper productMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.productMapper = productMapper;
    }



    public FakeStoreProductDTO[] getAllProducts() {
        //RestTemplate restTemplate = restTemplateBuilder.build();
        try {
            ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(fakeStoreUrl, FakeStoreProductDTO[].class);

            if(responseEntity == null){
                return null;
                // Throw a custom exception if the product is not found
               // throw new ProductNotFoundException("Product not found ");
            }

            return responseEntity.getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public FakeStoreProductDTO getProductByIdService(Long id) throws ProductNotFoundException {
        String specificProductUrl = fakeStoreUrl + "/" + id; // Construct the URL

        try {
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO.class);

            FakeStoreProductDTO productDTO = responseEntity.getBody();

            if (productDTO == null) {
                // Throw a custom exception if the product is not found
                throw new ProductNotFoundException("Product not found for id: " + id);
            }
            // Map the DTO to the Product entity
            return productDTO;

        } catch (ProductNotFoundException e) {
            throw e; // Re-throw custom exceptions
        } catch (Exception e) {
            // Handle other exceptions appropriately (e.g., log and throw a RuntimeException)
            throw new RuntimeException("An error occurred while fetching product by ID: " + id, e);
        }
    }



    public FakeStoreProductDTO[] getProductsLimited(int limit){
        String specificProductUrl = fakeStoreUrl +  "?limit=" + limit;
        try{
            ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO[].class);
            FakeStoreProductDTO[] productDTOs = responseEntity.getBody();

            return productDTOs;

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    public FakeStoreProductDTO[] getProductsSorted(String orderSort){
        String specificProductUrl = fakeStoreUrl +"?sort="+ orderSort;

        try{
            ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO[].class);
            FakeStoreProductDTO[] productDTOs = responseEntity.getBody();

            return productDTOs;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }



    public String[] getAllCategories() {
        String specificProductUrl = fakeStoreUrl + "/categories"; // URL to fetch categories from the API

        try {
            // Make a GET request to fetch the categories data (the response is a simple array of category names)
            ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(specificProductUrl, String[].class);
            String[] categoryNames = responseEntity.getBody();

            // Check if categories are returned
            if (categoryNames == null || categoryNames.length == 0) {
                // Throw a custom exception if categories are not found
                throw new ProductNotFoundException("Categories not found");
            }

            return categoryNames;
        } catch (Exception e) {
            // Handle any errors (e.g., network errors, mapping issues, etc.)
            throw new RuntimeException("Error fetching categories", e);
        }
    }


    public FakeStoreProductDTO[] getProductByCategory(String category){
        String specificProductUrl = fakeStoreUrl + "/category/"+category; // URL to fetch categories from the API
        try {
            // Make a GET request to fetch the categories data (the response is a simple array of category names)
            ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO[].class);
            FakeStoreProductDTO[] productDTO = responseEntity.getBody();


            return productDTO;

        } catch (Exception e) {
            // Handle other exceptions appropriately (e.g., log and throw a RuntimeException)
            throw new RuntimeException("An error occurred while fetching product by ID: " + category, e);
        }

    }



    public FakeStoreProductDTO addProduct(FakeStoreProductDTO fakeStoreProductDTO) {
        String specificProductUrl = fakeStoreUrl; // API URL to add the product

        try {
            // Convert Product to FakeStoreProductDTO (assume you have a mapper for this)
            //FakeStoreProductDTO fakeStoreProductDto = productMapper.mapProductToDTO(fakeStoreProductDTO);

            // Make the POST request
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.postForEntity(
                    specificProductUrl,
                    fakeStoreProductDTO,
                    FakeStoreProductDTO.class
            );

            // Extract the response body
            FakeStoreProductDTO responseBody = responseEntity.getBody();

            // If the response is null, throw an exception
            if (responseBody == null) {
                throw new RuntimeException("Failed to add the product. Response body is null.");
            }

            // Convert FakeStoreProductDTO back to Product and return
            // return productMapper.mapDTOToProduct(responseBody);

            return responseBody;
        } catch (Exception e) {
            // Handle any exceptions and rethrow them with a meaningful message
            throw new RuntimeException("Error occurred while adding the product: " + e.getMessage(), e);
        }
    }



    public FakeStoreProductDTO updateProduct(Long id, FakeStoreProductDTO updateDTO) {
        String specificProductUrl = fakeStoreUrl + "/" + id;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<FakeStoreProductDTO> requestEntity = new HttpEntity<>(updateDTO, headers);

            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.exchange(
                    specificProductUrl,
                    HttpMethod.PUT,
                    requestEntity,
                    FakeStoreProductDTO.class
            );

            return responseEntity.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error updating product with ID: " + id, e);
        }
    }


    public void deleteProductById(Long id) {
        String specificProductUrl = fakeStoreUrl + "/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                specificProductUrl,
                HttpMethod.DELETE,
                requestEntity,
                Void.class
        );

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to delete product. Status code: " + responseEntity.getStatusCode());
        }
    }


}
