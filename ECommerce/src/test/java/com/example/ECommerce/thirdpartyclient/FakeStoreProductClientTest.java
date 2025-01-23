package com.example.ECommerce.thirdpartyclient;

import com.example.ECommerce.exceptions.NotFoundException;
import com.example.ECommerce.exceptions.ProductNotFoundException;
import com.example.ECommerce.thirdPartyClient.FakeStoreDto.FakeStoreProductDTO;
import com.example.ECommerce.thirdPartyClient.FakeStoreProductClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FakeStoreProductClientTest {

    @Autowired
    private FakeStoreProductClient fakeStoreProductClient;

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    public void testGetProductById() throws NotFoundException, ProductNotFoundException {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        when(restTemplateBuilder.build()).thenReturn(restTemplate);

        ResponseEntity<FakeStoreProductDTO> responseMock = new ResponseEntity<>(null, HttpStatus.OK);

        when(restTemplate.getForEntity(any(String.class), eq(FakeStoreProductDTO.class), any(Long.class)))
                .thenReturn(responseMock);

        FakeStoreProductDTO response = fakeStoreProductClient.getProductByIdService(1L);

        Assertions.assertNull(response);

    }

//    @Test
//    public void testGetProductById() throws NotFoundException {
//        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
//        when(restTemplateBuilder.build()).thenReturn(restTemplate);
//
//        ResponseEntity<FakeStoreProductDTO> responseMock = new ResponseEntity<>(null, HttpStatus.OK);
//        when(restTemplate.getForEntity(any(), FakeStoreProductDTO.class, 1L)).thenReturn(responseMock);
//
//        Assertions.assertNull(responseMock);
//
////        when(restTemplate.getForEntity(any(String.class), eq(FakeStoreProductDTO.class), any(Long.class)))
////                .thenReturn(responseMock);
////
////        FakeStoreProductDTO response = fakeStoreProductClient.getProductByIdService(1L);
////
////        Assertions.assertNull(response);
//    }
}
