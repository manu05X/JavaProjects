package org.example.evaluations.controllers;

import org.example.evaluations.evaluation.controllers.SearchController;
import org.example.evaluations.evaluation.models.Flight;
import org.example.evaluations.evaluation.models.Size;
import org.example.evaluations.evaluation.services.OpenSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SearchController.class)
public class SearchControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OpenSearchService openSearchService;

    @Test
    public void testGetFlightsMatchingSearchQuery_WithValidTime() throws Exception {
        // Given
        String query = "14:30:00";
        int pageSize = 10;
        int pageNumber = 0;

        List<Flight> flights = new ArrayList<>();
        Flight flight = new Flight();
        flight.setFlightId("FL123");
        flight.setAirlinesName("Airline A");
        flight.setTime(Time.valueOf("14:30:00"));
        flight.setSize(Size.LARGE);
        flights.add(flight);

        Page<Flight> flightPage = new PageImpl<>(flights, PageRequest.of(pageNumber, pageSize), flights.size());

        // Mock the OpenSearchService behavior
        when(openSearchService.getFlightsMatchingSearchQuery(query, pageSize, pageNumber)).thenReturn(flightPage);

        // When & Then
        mockMvc.perform(get("/search/{query}", query)
                        .param("pageSize", String.valueOf(pageSize))
                        .param("pageNumber", String.valueOf(pageNumber)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].flightId").value("FL123"))
                .andExpect(jsonPath("$.content", org.hamcrest.Matchers.hasSize(1)));
    }

    @Test
    public void testGetFlightsMatchingSearchQuery_WithAirlinesName() throws Exception {
        // Given
        String query = "Airline A";
        int pageSize = 10;
        int pageNumber = 0;

        List<Flight> flights = new ArrayList<>();
        Flight flight = new Flight();
        flight.setSize(Size.MEDIUM);
        flight.setFlightId("FL124");
        flight.setAirlinesName("Airline A");
        flights.add(flight);

        Page<Flight> flightPage = new PageImpl<>(flights, PageRequest.of(pageNumber, pageSize), flights.size());

        // Mock the OpenSearchService behavior
        when(openSearchService.getFlightsMatchingSearchQuery(query, pageSize, pageNumber)).thenReturn(flightPage);

        // When & Then
        mockMvc.perform(get("/search/{query}", query)
                        .param("pageSize", String.valueOf(pageSize))
                        .param("pageNumber", String.valueOf(pageNumber)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].flightId").value("FL124"))
                .andExpect(jsonPath("$.content", org.hamcrest.Matchers.hasSize(1)));
    }

    @Test
    public void testGetFlightsMatchingSearchQuery_NoResults() throws Exception {
        // Given
        String query = "Non-existent";
        int pageSize = 10;
        int pageNumber = 0;

        List<Flight> flights = new ArrayList<>();
        Page<Flight> flightPage = new PageImpl<>(flights, PageRequest.of(pageNumber, pageSize), flights.size());

        // Mock the OpenSearchService behavior
        when(openSearchService.getFlightsMatchingSearchQuery(query, pageSize, pageNumber)).thenReturn(flightPage);

        // When & Then
        mockMvc.perform(get("/search/{query}", query)
                        .param("pageSize", String.valueOf(pageSize))
                        .param("pageNumber", String.valueOf(pageNumber)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", org.hamcrest.Matchers.hasSize(0))); // Expecting no flights
    }
}
