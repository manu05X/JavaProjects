package org.example.evaluations.clients;

import org.example.evaluations.evaluation.clients.FakeStoreClient;
import org.example.evaluations.evaluation.dtos.FakeStoreCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FakeStoreClientTests {

    @Autowired
    private FakeStoreClient fakeStoreClient;

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }

    @Test
    public void testGetCartById() {
        Long cartId = 1L;
        FakeStoreCart expectedCart = new FakeStoreCart();
        expectedCart.setId(1L);
        expectedCart.setUserId(2L);
        ResponseEntity<FakeStoreCart> responseEntity = new ResponseEntity<>(expectedCart, HttpStatus.OK);
        when(restTemplate.execute(
                eq("https://fakestoreapi.com/carts/{cartId}"),
                eq(HttpMethod.GET),
                any(),
                any(),
                eq(cartId)
        )).thenReturn(responseEntity);

        FakeStoreCart actualCart = fakeStoreClient.getCartById(cartId);

        verify(restTemplate).execute(
                eq("https://fakestoreapi.com/carts/{cartId}"),
                eq(HttpMethod.GET),
                any(),
                any(),
                eq(cartId)
        );
        assertEquals(expectedCart, actualCart);
    }

    @Test
    public void testGetCartsByUserId() {
        Long userId = 1L;
        FakeStoreCart[] expectedCarts = new FakeStoreCart[1];
        FakeStoreCart fakeStoreCart = new FakeStoreCart();
        fakeStoreCart.setUserId(1L);
        fakeStoreCart.setId(10L);
        fakeStoreCart.setProducts(new ArrayList<>());
        expectedCarts[0] = fakeStoreCart;
        ResponseEntity<FakeStoreCart[]> responseEntity = new ResponseEntity<>(expectedCarts, HttpStatus.OK);
        when(restTemplate.execute(
                eq("https://fakestoreapi.com/carts/user/{userId}"),
                eq(HttpMethod.GET),
                any(),
                any(),
                eq(userId)
        )).thenReturn(responseEntity);

        FakeStoreCart[] actualCarts = fakeStoreClient.getCartsByUserId(userId);

        verify(restTemplate).execute(
                eq("https://fakestoreapi.com/carts/user/{userId}"),
                eq(HttpMethod.GET),
                any(),
                any(),
                eq(userId)
        );
        assertArrayEquals(expectedCarts, actualCarts);
    }

    @Test
    public void testDeleteCartById() {
        Long cartId = 1L;
        FakeStoreCart expectedCart = new FakeStoreCart();
        expectedCart.setDate("2024-09-15T00:00:00.000Z");
        expectedCart.setUserId(3L);
        ResponseEntity<FakeStoreCart> responseEntity = new ResponseEntity<>(expectedCart, HttpStatus.OK);
        when(restTemplate.execute(
                eq("https://fakestoreapi.com/carts/{cartId}"),
                eq(HttpMethod.DELETE),
                any(),
                any(),
                eq(cartId)
        )).thenReturn(responseEntity);

        FakeStoreCart actualCart = fakeStoreClient.deleteCartById(cartId);

        verify(restTemplate).execute(
                eq("https://fakestoreapi.com/carts/{cartId}"),
                eq(HttpMethod.DELETE),
                any(),
                any(),
                eq(cartId)
        );
        assertEquals(expectedCart, actualCart);
    }

    @Test
    public void testUpdateCart() {
        Long cartId = 1L;
        FakeStoreCart requestCart = new FakeStoreCart(); 
        requestCart.setUserId(10L);
        requestCart.setId(1L);
        FakeStoreCart expectedCart = new FakeStoreCart();
        expectedCart.setId(1L);
        expectedCart.setUserId(10L);
        requestCart.setDate("2020-01-01T00:00:00.000Z");
        expectedCart.setDate("2020-01-01T00:00:00.000Z");
        ResponseEntity<FakeStoreCart> responseEntity = new ResponseEntity<>(expectedCart, HttpStatus.OK);
        when(restTemplate.execute(
                eq("https://fakestoreapi.com/carts/{cartId}"),
                eq(HttpMethod.PUT),
                any(),
                any(),
                eq(cartId)
        )).thenReturn(responseEntity);

        FakeStoreCart actualCart = fakeStoreClient.updateCart(cartId, requestCart);

        verify(restTemplate).execute(
                eq("https://fakestoreapi.com/carts/{cartId}"),
                eq(HttpMethod.PUT),
                any(),
                any(),
                eq(cartId)
        );
        assertEquals(expectedCart, actualCart);
    }

    @Test
    public void testAddCart() {
        FakeStoreCart requestCart = new FakeStoreCart();
        FakeStoreCart expectedCart = new FakeStoreCart();
        requestCart.setId(100L);
        requestCart.setUserId(999L);
        requestCart.setDate("2023-12-23T00:00:00.000Z");
        expectedCart.setDate("2023-12-23T00:00:00.000Z");
        expectedCart.setId(100L);
        expectedCart.setUserId(999L);
        ResponseEntity<FakeStoreCart> responseEntity = new ResponseEntity<>(expectedCart, HttpStatus.OK);
        when(restTemplate.execute(
                eq("https://fakestoreapi.com/carts"),
                eq(HttpMethod.POST),
                any(),
                any()
        )).thenReturn(responseEntity);

        FakeStoreCart actualCart = fakeStoreClient.addCart(requestCart);

        verify(restTemplate).execute(
                eq("https://fakestoreapi.com/carts"),
                eq(HttpMethod.POST),
                any(),
                any()
        );
        assertEquals(expectedCart, actualCart);
    }
}
