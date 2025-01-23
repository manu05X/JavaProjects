package com.example.ECommerce.controller;

import com.example.ECommerce.dto.GenericProductDto;
import com.example.ECommerce.exceptions.ProductNotFoundException;
import com.example.ECommerce.model.Product;
import com.example.ECommerce.service.ProductService;
import com.example.ECommerce.thirdPartyClient.FakeStoreDto.FakeStoreProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/*
public class ProductControllerTest {

    //----------------------------------------------------------------------

    private ProductController productController;

    private ProductService productServiceMock;

    private ProductControllerTest() throws ProductNotFoundException {
        productServiceMock = Mockito.mock(ProductService.class);
        productController = new ProductController(productServiceMock); // we are passing the mock product service in ProductController so actual ProductService will  ot be called
    }


    @Test
    public void testGetProductByIdReturnsEmptyObjectWhenNoProductIsFound() throws ProductNotFoundException {
        // Mock behavior for productServiceMock when no product is found
        //when(productServiceMock.getProductByIdService(1L)).thenReturn(null);
        when(productServiceMock.getProductByIdService(any(Long.class))).thenReturn(null);


        // Act: Call the method on the controller
        Product response = productController.getProductById(1L);

        // Assert: Product should be null when no product is found
        // Assertions.assertNull(response, "Product should be null when no product is found");
        // Assert: Product should be not null when product is found
        Assertions.assertNotNull(response, "Product should be not null when product is found");
    }


}
 */



@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController; // Inject the actual ProductController

    @Autowired
    private ProductService getProductServiceMock;

    @MockBean
    private ProductService productServiceMock; // Mock the ProductService, this will be injected into ProductController

    @Test
    public void testGetProductByIdReturnsEmptyObjectWhenNoProductIsFound() throws ProductNotFoundException {
        // Mock behavior for productServiceMock when no product is found
        when(productServiceMock.getProductByIdService(any(Long.class))).thenReturn(null);

        // Act: Call the method on the controller
        GenericProductDto response = productController.getProductById(1L);

        // Assert: Product should be null when no product is found
        // Assertions.assertNull(response, "Product should be null when no product is found");
        // Assert: Product should be not null when product is found
        Assertions.assertNotNull(response, "Product should be not null when product is found");
    }


    @Test
    public void testGetProductByIdReturnsCorrectResponse() throws ProductNotFoundException{
        // Create the expected output DTO with the expected values
        GenericProductDto expectedOutput = new GenericProductDto();
        expectedOutput.setId(1L);  // Set expected product ID
        expectedOutput.setTitle("iPhone");  // Set expected product title

        // Create the product DTO that will be returned by the mock service
        GenericProductDto toBeReturned = new GenericProductDto();
        toBeReturned.setId(1L);  // Set ID of the returned product
        toBeReturned.setTitle("iPhone");  // Set title of the returned product

        // Mock the behavior of the ProductService to return the 'toBeReturned' DTO when called
        when(productServiceMock.getProductByIdService(any())).thenReturn(toBeReturned);

        // Call the method on the controller to get the response
        GenericProductDto response = productController.getProductById(1L);

        // Assert that the title of the response matches the expected title
        Assertions.assertEquals(response.getTitle(), expectedOutput.getTitle());
    }

}