package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.CartController;
import org.example.evaluations.evaluation.models.Cart;
import org.example.evaluations.evaluation.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(CartController.class)
public class CartControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testGetCartByUserIdAndCartId_InvalidUserId() throws Exception {
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/cart/abc/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("Please pass userId or cartId in correct format"));
    }

    @Test
    void testGetCartByUserIdAndCartId_InvalidCartId() throws Exception {
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/cart/123/abc")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("Please pass userId or cartId in correct format"));
    }

    @Test
    void testGetCartById_ZeroCartId() throws Exception {
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/cart/0")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Incorrect cart Id passed"));
    }

    @Test
    void testGetCartById_NegativeCartId() throws Exception {
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/cart/-1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Incorrect cart Id passed"));
    }

    @Test
    void testCreateCart_EmptyUniqueItemsList() throws Exception {
        // Arrange
        Cart cartRequest = new Cart();
        cartRequest.setFriendlyName("My Cart");
        cartRequest.setTotalValue(100.0);
        cartRequest.setId(1L);
        User user = new User();
        user.setName("user");
        user.setId(121L);
        cartRequest.setUser(user);

        List<String> uniqueItems = new ArrayList<>();
        cartRequest.setUniqueItems(uniqueItems);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartRequest)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Something went bad at our side"));
    }

    @Test
    void testCreateCart_NullUser() throws Exception {
        // Arrange
        Cart cartRequest = new Cart();
        cartRequest.setFriendlyName("My Cart");
        cartRequest.setTotalValue(100.0);
        cartRequest.setId(1L);
        List<String> uniqueItems = new ArrayList<>();
        uniqueItems.add("pens");
        cartRequest.setUniqueItems(uniqueItems);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("No User associated with Cart"));
    }

    @Test
    void testCreateCart_NullUniqueItemsList() throws Exception {
        // Arrange
        Cart cartRequest = new Cart();
        cartRequest.setFriendlyName("My Cart");
        cartRequest.setTotalValue(100.0);
        cartRequest.setId(1L);
        User user = new User();
        user.setName("user");
        user.setId(121L);
        cartRequest.setUser(user);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Cannot invoke \"java.util.List.size()\" because the return value of \"org.example.evaluations.evaluation.models.Cart.getUniqueItems()\" is null"));
    }
}
