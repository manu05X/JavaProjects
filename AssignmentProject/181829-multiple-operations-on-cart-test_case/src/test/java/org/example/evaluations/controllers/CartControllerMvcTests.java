package org.example.evaluations.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.CartController;
import org.example.evaluations.evaluation.models.Cart;
import org.example.evaluations.evaluation.services.ICartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
public class CartControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICartService cartService;

    @Test
    public void testGetCartById() throws Exception {
        Long cartId = 1L;
        Cart cart = createCart(cartId);
        when(cartService.getCartById(cartId)).thenReturn(cart);

        mockMvc.perform(get("/cart/{cartId}", cartId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cartId))
                .andExpect(jsonPath("$.userId").value(cart.getUserId()));
    }

    @Test
    public void testGetCartByUserId() throws Exception {
        Long userId = 1L;
        List<Cart> carts = Arrays.asList(createCart(1L), createCart(2L));
        when(cartService.getCartByUserId(userId)).thenReturn(carts);

        mockMvc.perform(get("/cart/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(userId))
                .andExpect(jsonPath("$[1].userId").value(userId));
    }

    @Test
    public void testDeleteCartById() throws Exception {
        Long cartId = 1L;
        Cart cart = createCart(cartId);
        when(cartService.deleteCartById(cartId)).thenReturn(cart);

        mockMvc.perform(delete("/cart/{cartId}", cartId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cartId));
    }

    @Test
    public void testAddCart() throws Exception {
        Cart cart = createCart(null);
        Cart savedCart = createCart(1L);
        when(cartService.addCart(cart)).thenReturn(savedCart);

        mockMvc.perform(post("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cart)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedCart.getId()));
    }

    @Test
    public void testUpdateCart() throws Exception {
        Long cartId = 1L;
        Cart cartRequest = createCart(cartId);
        Cart updatedCart = createCart(cartId);
        when(cartService.updateCart(cartId, cartRequest)).thenReturn(updatedCart);

        mockMvc.perform(put("/cart/{cartId}", cartId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cartRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cartId));
    }

    private Cart createCart(Long id) {
        Cart cart = new Cart();
        cart.setId(id);
        cart.setDate(new Date());
        cart.setUserId(1L);
        cart.setProducts(Map.of(1L, 10.0, 2L, 20.0));
        return cart;
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
