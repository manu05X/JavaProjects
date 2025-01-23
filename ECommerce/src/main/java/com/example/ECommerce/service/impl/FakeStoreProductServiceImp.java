package com.example.ECommerce.service.impl;

import com.example.ECommerce.dto.GenericProductDto;
import com.example.ECommerce.thirdPartyClient.FakeStoreDto.FakeStoreProductDTO;
import com.example.ECommerce.exceptions.ProductNotFoundException;
import com.example.ECommerce.mapper.ProductMapper;
import com.example.ECommerce.model.Category;
import com.example.ECommerce.model.Product;
import com.example.ECommerce.service.ProductService;
import com.example.ECommerce.thirdPartyClient.FakeStoreProductClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductServiceImp implements ProductService {

    //private final RestTemplateBuilder restTemplateBuilder;
    //private final RestTemplate restTemplate; // RestTemplate at the class level

    private FakeStoreProductClient fakeStoreProductClient;

    private final ProductMapper productMapper; // Injected Mapper
    // private final String fakeStoreUrl = "https://fakestoreapi.com/products";

//    @Value("${product.fakestore.api.url}")
//    private String fakeStoreUrl;

    public FakeStoreProductServiceImp(ProductMapper productMapper, FakeStoreProductClient fakeStoreProductClient) {
        this.productMapper = productMapper;
        this.fakeStoreProductClient = fakeStoreProductClient;
    }

    /*
    @Override
    public List<Product> getAllProducts() {
        //RestTemplate restTemplate = restTemplateBuilder.build();
        try {
            ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(fakeStoreUrl, FakeStoreProductDTO[].class);
            FakeStoreProductDTO[] productDTOs = responseEntity.getBody();

            List<Product> products = new LinkedList<>();
            if (productDTOs != null) {
                for (FakeStoreProductDTO dto : productDTOs) {
                    products.add(productMapper.mapDTOToProduct(dto)); // Use mapper
                }
            }
            return products;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
     */


    @Override
    public List<Product> getAllProducts() {
        //RestTemplate restTemplate = restTemplateBuilder.build();
        try {
            List<Product> products = new LinkedList<>();
            FakeStoreProductDTO[] productDTOs = fakeStoreProductClient.getAllProducts();

            if (productDTOs != null) {
                for (FakeStoreProductDTO dto : productDTOs) {
                    products.add(productMapper.mapDTOToProduct(dto)); // Use mapper
                }
            }
            return products;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

/*
    @Override
    public Product getProductByIdService(Long id) throws ProductNotFoundException {
        String specificProductUrl = fakeStoreUrl + "/" + id; // Construct the URL

        try {
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO.class);

            FakeStoreProductDTO productDTO = responseEntity.getBody();

            if (productDTO == null) {
                // Throw a custom exception if the product is not found
                throw new ProductNotFoundException("Product not found for id: " + id);
            }

            // Map the DTO to the Product entity
            return productMapper.mapDTOToProduct(productDTO);

        } catch (ProductNotFoundException e) {
            throw e; // Re-throw custom exceptions
        } catch (Exception e) {
            // Handle other exceptions appropriately (e.g., log and throw a RuntimeException)
            throw new RuntimeException("An error occurred while fetching product by ID: " + id, e);
        }
    }
 */

    @Override
    public GenericProductDto getProductByIdService(Long id) throws ProductNotFoundException {
        System.out.println("Calling the Service in test....");
        // String specificProductUrl = fakeStoreUrl + "/" + id; // Construct the URL

        try {
            FakeStoreProductDTO productDTO = fakeStoreProductClient.getProductByIdService(id);

            if (productDTO == null) {
                return  null;
                // Throw a custom exception if the product is not found
                //throw new ProductNotFoundException("Product not found for id: " + id);
            }

            Product product = productMapper.mapDTOToProduct(productDTO);

            // Map the DTO to the Product entity
            return productMapper.mapProductToGenericDTO(product);

        } catch (ProductNotFoundException e) {
            throw e; // Re-throw custom exceptions
        } catch (Exception e) {
            // Handle other exceptions appropriately (e.g., log and throw a RuntimeException)
            throw new RuntimeException("An error occurred while fetching product by ID: " + id, e);
        }
    }


    /*
    @Override
    public List<Product> getProductsLimited(int limit){
        String specificProductUrl = fakeStoreUrl +  "?limit=" + limit;
        try{
            ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO[].class);
            FakeStoreProductDTO[] productDTOs = responseEntity.getBody();

            List<Product> products = new LinkedList<>();
            if (productDTOs != null) {
                for (FakeStoreProductDTO dto : productDTOs) {
                    products.add(productMapper.mapDTOToProduct(dto)); // Use mapper
                }
            }
            return products;

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
     */

    // Get ProductLimit
    @Override
    public List<Product> getProductsLimited(int limit){
        //String specificProductUrl = fakeStoreUrl +  "?limit=" + limit;
        try{
            FakeStoreProductDTO[] productDTOs = fakeStoreProductClient.getProductsLimited(limit);

            List<Product> products = new LinkedList<>();
            if (productDTOs != null) {
                for (FakeStoreProductDTO dto : productDTOs) {
                    products.add(productMapper.mapDTOToProduct(dto)); // Use mapper
                }
            }
            return products;

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    /*
    //https://fakestoreapi.com/products?sort=desc
    @Override
    public List<Product> getProductsSorted(String orderSort){
        String specificProductUrl = fakeStoreUrl +"?sort="+ orderSort;

        try{
            ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO[].class);
            FakeStoreProductDTO[] productDTOs = responseEntity.getBody();

            List<Product> products = new LinkedList<>();

            if (productDTOs != null) {
                for (FakeStoreProductDTO dto : productDTOs) {
                    products.add(productMapper.mapDTOToProduct(dto)); // Use mapper
                }
            }
            return products;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
     */

    // Get Product Sorted

    @Override
    public List<Product> getProductsSorted(String orderSort){
        //String specificProductUrl = fakeStoreUrl +"?sort="+ orderSort;

        try{
            //ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO[].class);
            FakeStoreProductDTO[] productDTOs = fakeStoreProductClient.getProductsSorted(orderSort);

            List<Product> products = new LinkedList<>();

            if (productDTOs != null) {
                for (FakeStoreProductDTO dto : productDTOs) {
                    products.add(productMapper.mapDTOToProduct(dto)); // Use mapper
                }
            }
            return products;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /*
        Sorting Logic: The sorting functionality depends on how the external API supports sorting via query parameters.
        This implementation assumes that the external API supports the ?sort= query parameter, as shown.
    */

    // Get Product By Categories
    /*
    @Override
    public List<Category> getAllCategories() {
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

            // Map the response to a list of Category objects
            List<Category> categories = new LinkedList<>();
            for (String categoryName : categoryNames) {
                Category category = new Category(categoryName);  // Constructor works with the category name
                categories.add(category); // Add to the list
            }

            return categories;
        } catch (Exception e) {
            // Handle any errors (e.g., network errors, mapping issues, etc.)
            throw new RuntimeException("Error fetching categories", e);
        }
    }
     */

    @Override
    public List<Category> getAllCategories() {
        //String specificProductUrl = fakeStoreUrl + "/categories"; // URL to fetch categories from the API

        try {
            // Make a GET request to fetch the categories data (the response is a simple array of category names)
            //ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(specificProductUrl, String[].class);
            String[] categoryNames = fakeStoreProductClient.getAllCategories();

            // Check if categories are returned
            if (categoryNames == null || categoryNames.length == 0) {
                // Throw a custom exception if categories are not found
                throw new ProductNotFoundException("Categories not found");
            }

            // Map the response to a list of Category objects
            List<Category> categories = new LinkedList<>();
            for (String categoryName : categoryNames) {
                Category category = new Category(categoryName);  // Constructor works with the category name
                categories.add(category); // Add to the list
            }

            return categories;
        } catch (Exception e) {
            // Handle any errors (e.g., network errors, mapping issues, etc.)
            throw new RuntimeException("Error fetching categories", e);
        }
    }


    // Get Product By ID
    /*
    @Override
    public List<Product> getProductByCategory(String category){
        String specificProductUrl = fakeStoreUrl + "/category/"+category; // URL to fetch categories from the API
        try {
            // Make a GET request to fetch the categories data (the response is a simple array of category names)
            ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO[].class);
            FakeStoreProductDTO[] productDTO = responseEntity.getBody();

            List<Product> products = new LinkedList<>();
            if (productDTO != null) {
                for (FakeStoreProductDTO dto : productDTO) {
                    products.add(productMapper.mapDTOToProduct(dto)); // Use mapper
                }
            }
            return products;

        } catch (Exception e) {
            // Handle other exceptions appropriately (e.g., log and throw a RuntimeException)
            throw new RuntimeException("An error occurred while fetching product by ID: " + category, e);
        }

    }
    */

    @Override
    public List<Product> getProductByCategory(String category){
        //String specificProductUrl = fakeStoreUrl + "/category/"+category; // URL to fetch categories from the API
        try {
            // Make a GET request to fetch the categories data (the response is a simple array of category names)
            //ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(specificProductUrl, FakeStoreProductDTO[].class);
            FakeStoreProductDTO[] productDTO = fakeStoreProductClient.getProductByCategory(category);

            List<Product> products = new LinkedList<>();
            if (productDTO != null) {
                for (FakeStoreProductDTO dto : productDTO) {
                    products.add(productMapper.mapDTOToProduct(dto)); // Use mapper
                }
            }
            return products;

        } catch (Exception e) {
            // Handle other exceptions appropriately (e.g., log and throw a RuntimeException)
            throw new RuntimeException("An error occurred while fetching product by ID: " + category, e);
        }

    }


    //Add Product
    /*
    @Override
    public Product addProduct(Product product) {
        String specificProductUrl = fakeStoreUrl; // API URL to add the product

        try {
            // Convert Product to FakeStoreProductDTO (assume you have a mapper for this)
            FakeStoreProductDTO fakeStoreProductDto = productMapper.mapProductToDTO(product);

            // Make the POST request
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.postForEntity(
                    specificProductUrl,
                    fakeStoreProductDto,
                    FakeStoreProductDTO.class
            );

            // Extract the response body
            FakeStoreProductDTO responseBody = responseEntity.getBody();

            // If the response is null, throw an exception
            if (responseBody == null) {
                throw new RuntimeException("Failed to add the product. Response body is null.");
            }

            // Convert FakeStoreProductDTO back to Product and return
            return productMapper.mapDTOToProduct(responseBody);
        } catch (Exception e) {
            // Handle any exceptions and rethrow them with a meaningful message
            throw new RuntimeException("Error occurred while adding the product: " + e.getMessage(), e);
        }
    }

     */

    @Override
    public Product addProduct(Product product) {
        //String specificProductUrl = fakeStoreUrl; // API URL to add the product

        try {
            // Convert Product to FakeStoreProductDTO (assume you have a mapper for this)
            FakeStoreProductDTO fakeStoreProductDto = productMapper.mapProductToDTO(product);

            // Calling the FakeStoreProductClient to add FakeStoreProductDTO
            FakeStoreProductDTO responseBody = fakeStoreProductClient.addProduct(fakeStoreProductDto);

            // If the response is null, throw an exception
            if (responseBody == null) {
                throw new RuntimeException("Failed to add the product. Response body is null.");
            }

            // Convert FakeStoreProductDTO back to Product and return to controller
            return productMapper.mapDTOToProduct(responseBody);
        } catch (Exception e) {
            // Handle any exceptions and rethrow them with a meaningful message
            throw new RuntimeException("Error occurred while adding the product: " + e.getMessage(), e);
        }
    }



    /*
    @Override
    public Product updateProduct(Long id, Product product) {
        String specificProductUrl = fakeStoreUrl + "/" + id; // Construct the URL with the product ID

        try {
            // Fetch the existing product to ensure it exists (optional step, but good for validation)
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(
                    specificProductUrl,
                    FakeStoreProductDTO.class
            );

            FakeStoreProductDTO existingProductDTO = responseEntity.getBody();

            // If the existing product is not found, throw an exception
            if (existingProductDTO == null) {
                throw new ProductNotFoundException("Product not found with ID: " + id);
            }

            // Convert Product to FakeStoreProductDTO for the update request
            FakeStoreProductDTO updatedProductDTO = productMapper.mapProductToDTO(product);

            // Make the PUT request to update the product
            restTemplate.put(specificProductUrl, updatedProductDTO);

            // Fetch the updated product details from the API (optional step, ensures the update is successful)
            ResponseEntity<FakeStoreProductDTO> updatedResponse = restTemplate.getForEntity(
                    specificProductUrl,
                    FakeStoreProductDTO.class
            );

            FakeStoreProductDTO updatedResponseBody = updatedResponse.getBody();

            // Convert the updated FakeStoreProductDTO back to Product and return
            if (updatedResponseBody != null) {
                return productMapper.mapDTOToProduct(updatedResponseBody);
            } else {
                throw new RuntimeException("Failed to fetch updated product details.");
            }
        } catch (Exception e) {
            // Handle any exceptions and rethrow them with a meaningful message
            throw new RuntimeException("Error occurred while updating the product: " + e.getMessage(), e);
        }
    }

*/


    /*
    // Actual  Update code
    @Override
    public Product updateProduct(Long id, Product product) {
        String specificProductUrl = fakeStoreUrl + "/" + id;
        try {
            // Fetch the existing product to ensure it exists
            ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(
                    specificProductUrl,
                    FakeStoreProductDTO.class
            );
            FakeStoreProductDTO existingProductDTO = responseEntity.getBody();

            if (existingProductDTO == null) {
                throw new ProductNotFoundException("Product not found with ID: " + id);
            }

            // Create a DTO for the update request
            FakeStoreProductDTO updateDTO = new FakeStoreProductDTO();
            updateDTO.setTitle(product.getTitle());
            updateDTO.setPrice(product.getPrice().doubleValue());
            updateDTO.setDescription(product.getDescription());
            updateDTO.setCategory(product.getCategory());
            updateDTO.setImage(product.getImage());

            // Handle rating update with null check
            if (product.getRating() != null) {
                FakeStoreProductDTO.RatingDTO ratingDTO = new FakeStoreProductDTO.RatingDTO();
                // If new rate is null, use existing rate
                if (product.getRating().getRate() == null && existingProductDTO.getRating() != null) {
                    ratingDTO.setRate(existingProductDTO.getRating().getRate());
                } else {
                    ratingDTO.setRate(product.getRating().getRate());
                }

                // If new count is 0, use existing count
                if (product.getRating().getCount() == 0 && existingProductDTO.getRating() != null) {
                    ratingDTO.setCount(existingProductDTO.getRating().getCount());
                } else {
                    ratingDTO.setCount(product.getRating().getCount());
                }

                updateDTO.setRating(ratingDTO);
            } else {
                // If no new rating is provided, keep the existing rating
                updateDTO.setRating(existingProductDTO.getRating());
            }

            // Make the PUT request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<FakeStoreProductDTO> requestEntity = new HttpEntity<>(updateDTO, headers);

            ResponseEntity<FakeStoreProductDTO> updateResponse = restTemplate.exchange(
                    specificProductUrl,
                    HttpMethod.PUT,
                    requestEntity,
                    FakeStoreProductDTO.class
            );

            FakeStoreProductDTO updatedProduct = updateResponse.getBody();

            if (updatedProduct != null) {
                return productMapper.mapDTOToProduct(updatedProduct);
            } else {
                throw new RuntimeException("Failed to fetch updated product details.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while updating the product: " + e.getMessage(), e);
        }
    }
    */

    // Actual Update
    @Override
    public Product updateProduct(Long id, Product product) {
        try {
            // Fetch existing product to ensure it exists
            FakeStoreProductDTO existingProductDTO = fakeStoreProductClient.getProductByIdService(id);

            if (existingProductDTO == null) {
                throw new ProductNotFoundException("Product not found with ID: " + id);
            }

            // Create DTO for the update request
            FakeStoreProductDTO updateDTO = new FakeStoreProductDTO();
            updateDTO.setTitle(product.getTitle());
            updateDTO.setPrice(product.getPrice());
            updateDTO.setDescription(product.getDescription());
            updateDTO.setCategory(product.getCategory());
            updateDTO.setImage(product.getImage());

            // Handle rating updates
            if (product.getRating() != null) {
                FakeStoreProductDTO.RatingDTO ratingDTO = new FakeStoreProductDTO.RatingDTO();
                ratingDTO.setRate(product.getRating().getRate() != null
                        ? product.getRating().getRate()
                        : (existingProductDTO.getRating() != null ? existingProductDTO.getRating().getRate() : null));
                ratingDTO.setCount(product.getRating().getCount() != 0
                        ? product.getRating().getCount()
                        : (existingProductDTO.getRating() != null ? existingProductDTO.getRating().getCount() : 0));

                updateDTO.setRating(ratingDTO);
            } else {
                updateDTO.setRating(existingProductDTO.getRating());
            }

            // Call client to update the product
            FakeStoreProductDTO updatedProductDTO = fakeStoreProductClient.updateProduct(id, updateDTO);

            // Convert updated DTO to Product and return
            if (updatedProductDTO != null) {
                return productMapper.mapDTOToProduct(updatedProductDTO);
            } else {
                throw new RuntimeException("Failed to fetch updated product details.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while updating the product: " + e.getMessage(), e);
        }
    }


    /*
    @Override
    public Product updateProduct(Long id, FakeStoreProductDTO productRequestDto) {
        //RestTemplate restTemplate = .createRestTemplate();

        // Create the URL by appending the id to the base URL
        String url = "https://fakestoreapi.com/products/" + id;

        HttpEntity<FakeStoreProductDTO> requestEntity = new HttpEntity<>(productRequestDto);

        // Use exchange to send the PUT request
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDTO.class);

        FakeStoreProductDTO productResponseDto = responseEntity.getBody();

        if (productResponseDto != null) {
            return this.productMapper.mapDTOToProduct(productResponseDto);
        }

        throw new RuntimeException("Failed to update product");
    }
     */


    /*
    @Override
    public void deleteProduct(Long id) {
        String specificProductUrl = fakeStoreUrl + "/" + id;
        try {
            // First check if the product exists
            ResponseEntity<FakeStoreProductDTO> checkProduct = restTemplate.getForEntity(
                    specificProductUrl,
                    FakeStoreProductDTO.class
            );

            if (checkProduct.getBody() == null) {
                throw new ProductNotFoundException("Product not found with ID: " + id);
            }

            // Create headers for the delete request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            // Make the DELETE request
            ResponseEntity<Void> response = restTemplate.exchange(
                    specificProductUrl,
                    HttpMethod.DELETE,
                    requestEntity,
                    Void.class
            );

            // Check if deletion was successful
            if (response.getStatusCode().is2xxSuccessful()) {
                return; // Deletion successful
            } else {
                throw new RuntimeException("Failed to delete product. Status code: " + response.getStatusCode());
            }

        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting the product: " + e.getMessage(), e);
        }
    }
     */


    //Delete
    /*
    // In FakeStoreProductServiceImp:
    @Override
    public String deleteProduct(Long id) {
        String specificProductUrl = fakeStoreUrl + "/" + id;
        try {
            // First check if the product exists and store its title
            ResponseEntity<FakeStoreProductDTO> checkProduct = restTemplate.getForEntity(
                    specificProductUrl,
                    FakeStoreProductDTO.class
            );

            if (checkProduct.getBody() == null) {
                throw new ProductNotFoundException("Product not found with ID: " + id);
            }

            String productTitle = checkProduct.getBody().getTitle(); // Store the title for the confirmation message

            // Create headers for the delete request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            // Make the DELETE request
            ResponseEntity<Void> response = restTemplate.exchange(
                    specificProductUrl,
                    HttpMethod.DELETE,
                    requestEntity,
                    Void.class
            );

            // Check if deletion was successful and return appropriate message
            if (response.getStatusCode().is2xxSuccessful()) {
                return String.format("Product with ID %d ('%s') has been successfully deleted", id, productTitle);
            } else {
                throw new RuntimeException("Failed to delete product. Status code: " + response.getStatusCode());
            }

        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting the product: " + e.getMessage(), e);
        }
    }
     */


    @Override
    public String deleteProduct(Long id) {
        try {
            // Fetch the product to verify it exists and get its title
            FakeStoreProductDTO productDTO = fakeStoreProductClient.getProductByIdService(id);
            String productTitle = productDTO.getTitle();

            // Call the client to delete the product
            fakeStoreProductClient.deleteProductById(id);

            // Return confirmation message
            return String.format("Product with ID %d ('%s') has been successfully deleted", id, productTitle);

        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting the product: " + e.getMessage(), e);
        }
    }
}
