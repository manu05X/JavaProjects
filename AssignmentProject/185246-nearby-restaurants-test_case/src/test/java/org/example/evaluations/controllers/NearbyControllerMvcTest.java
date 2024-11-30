package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.NearbyController;
import org.example.evaluations.evaluation.dtos.UserLocationDto;
import org.example.evaluations.evaluation.models.Restaurant;
import org.example.evaluations.evaluation.services.NearbyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NearbyController.class)
public class NearbyControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NearbyService nearbyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddLocation() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");
        restaurant.setLatitude(40.7128);
        restaurant.setLongitude(-74.0060);
        restaurant.setUserRatings(4.5);
        restaurant.setAddress("123 Main St");
        restaurant.setPhoneNumber("123-456-7890");

        when(nearbyService.addLocation(any(Restaurant.class))).thenReturn(true);

        mockMvc.perform(post("/nearby/restaurants/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurant)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));

        verify(nearbyService, times(1)).addLocation(any(Restaurant.class));
    }

    @Test
    public void testFindAllNearbyRestaurants() throws Exception {
        UserLocationDto userLocationDto = new UserLocationDto();
        userLocationDto.setLatitude(40.7128);
        userLocationDto.setLongitude(-74.0060);
        userLocationDto.setRadius(5.0);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setId(1L);
        restaurant1.setName("Restaurant A");
        restaurant1.setLatitude(40.7128);
        restaurant1.setLongitude(-74.0060);
        restaurant1.setUserRatings(4.5);
        restaurant1.setAddress("123 Main St");
        restaurant1.setPhoneNumber("123-456-7890");

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(2L);
        restaurant2.setName("Restaurant B");
        restaurant2.setLatitude(40.7138);
        restaurant2.setLongitude(-74.0070);
        restaurant2.setUserRatings(4.0);
        restaurant2.setAddress("456 Elm St");
        restaurant2.setPhoneNumber("987-654-3210");

        List<Restaurant> nearbyRestaurants = Arrays.asList(restaurant1, restaurant2);
        when(nearbyService.findNearbyLocations(anyDouble(), anyDouble(), anyDouble())).thenReturn(nearbyRestaurants);

        mockMvc.perform(post("/nearby/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userLocationDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Restaurant A"))
                .andExpect(jsonPath("$[1].name").value("Restaurant B"));

        verify(nearbyService, times(1)).findNearbyLocations(anyDouble(), anyDouble(), anyDouble());
    }

}
