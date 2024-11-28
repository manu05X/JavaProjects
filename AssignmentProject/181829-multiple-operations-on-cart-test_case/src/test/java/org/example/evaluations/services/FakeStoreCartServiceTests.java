package org.example.evaluations.services;

import org.example.evaluations.evaluation.clients.FakeStoreClient;
import org.example.evaluations.evaluation.dtos.FakeStoreCart;
import org.example.evaluations.evaluation.dtos.FakeStoreProduct;
import org.example.evaluations.evaluation.models.Cart;
import org.example.evaluations.evaluation.services.FakeStoreCartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FakeStoreCartServiceTests {
    @Autowired
    private FakeStoreCartService fakeStoreCartService;

    @MockBean
    private FakeStoreClient fakeStoreClient;

    @Test
    public void testGetCartById() {
        Long cartId = 1L;
        FakeStoreCart fakeStoreCart = createFakeStoreCart(cartId);
        when(fakeStoreClient.getCartById(cartId)).thenReturn(fakeStoreCart);

        Cart cart = fakeStoreCartService.getCartById(cartId);

        verify(fakeStoreClient).getCartById(cartId);
        assertNotNull(cart);
        assertEquals(cartId, cart.getId());
    }

    @Test
    public void testGetCartByUserId() {
        Long userId = 1L;
        FakeStoreCart[] fakeStoreCarts = { createFakeStoreCart(1L), createFakeStoreCart(2L) };
        when(fakeStoreClient.getCartsByUserId(userId)).thenReturn(fakeStoreCarts);

        List<Cart> carts = fakeStoreCartService.getCartByUserId(userId);

        verify(fakeStoreClient).getCartsByUserId(userId);
        assertNotNull(carts);
        assertEquals(2, carts.size());
    }

    @Test
    public void testDeleteCartById() {
        Long cartId = 1L;
        FakeStoreCart fakeStoreCart = createFakeStoreCart(cartId);
        when(fakeStoreClient.deleteCartById(cartId)).thenReturn(fakeStoreCart);

        Cart cart = fakeStoreCartService.deleteCartById(cartId);

        verify(fakeStoreClient).deleteCartById(cartId);
        assertNotNull(cart);
        assertEquals(cartId, cart.getId());
    }

    @Test
    public void testUpdateCart() {
        Long cartId = 1L;
        Cart cartRequest = createCart(cartId);
        FakeStoreCart fakeStoreResponse = createFakeStoreCart(cartId);
        when(fakeStoreClient.updateCart(eq(cartId), any(FakeStoreCart.class))).thenReturn(fakeStoreResponse);

        Cart updatedCart = fakeStoreCartService.updateCart(cartId, cartRequest);

        verify(fakeStoreClient).updateCart(eq(cartId), any(FakeStoreCart.class));
        assertNotNull(updatedCart);
        assertEquals(cartId, updatedCart.getId());
    }

    @Test
    public void testAddCart() {
        Cart cartRequest = createCart(null);
        FakeStoreCart fakeStoreResponse = createFakeStoreCart(1L);
        when(fakeStoreClient.addCart(any(FakeStoreCart.class))).thenReturn(fakeStoreResponse);

        Cart addedCart = fakeStoreCartService.addCart(cartRequest);

        verify(fakeStoreClient).addCart(any(FakeStoreCart.class));
        assertNotNull(addedCart);
        assertEquals(fakeStoreResponse.getId(), addedCart.getId());
    }

    private FakeStoreCart createFakeStoreCart(Long id) {
        FakeStoreCart fakeStoreCart = new FakeStoreCart();
        fakeStoreCart.setId(id);
        fakeStoreCart.setDate(Instant.now().toString());
        fakeStoreCart.setUserId(1L);
        FakeStoreProduct fakeStoreProduct1 = new FakeStoreProduct();
        fakeStoreProduct1.setProductId(1L);
        fakeStoreProduct1.setQuantity(10D);
        FakeStoreProduct fakeStoreProduct2 = new FakeStoreProduct();
        fakeStoreProduct2.setProductId(2L);
        fakeStoreProduct2.setQuantity(20D);

        List<FakeStoreProduct> products = Arrays.asList(
               fakeStoreProduct1,fakeStoreProduct2
        );
        fakeStoreCart.setProducts(products);
        return fakeStoreCart;
    }

    private Cart createCart(Long id) {
        Cart cart = new Cart();
        cart.setId(id);
        cart.setDate(Date.from(Instant.now()));
        cart.setUserId(1L);
        Map<Long, Double> products = new HashMap<>();
        products.put(1L, 10.0);
        products.put(2L, 20.0);
        cart.setProducts(products);
        return cart;
    }
}
