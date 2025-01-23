package com.example.ECommerce.service.impl;

import com.example.ECommerce.dto.GenericProductDto;
import com.example.ECommerce.exceptions.NotFoundException;
import com.example.ECommerce.exceptions.ProductNotFoundException;
import com.example.ECommerce.thirdPartyClient.FakeStoreProductClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FakeStoreProductServiceTest {

    @Autowired
    private FakeStoreProductServiceImp fakeStoreProductServiceImp;

    @MockBean
    private FakeStoreProductClient fakeStoreProductClient;


    @Test
    public void testGetProductByIdWhenClientReturnsNull() throws ProductNotFoundException {
        when(fakeStoreProductClient.getProductByIdService(any()))
                .thenReturn(null);

        GenericProductDto response = fakeStoreProductServiceImp.getProductByIdService(1L);

        Assertions.assertNull(response);

//        Assertions.assertThrows(ProductNotFoundException.class,
//                () -> fakeStoreProductServiceImp.getProductByIdService(1L));
    }

}
