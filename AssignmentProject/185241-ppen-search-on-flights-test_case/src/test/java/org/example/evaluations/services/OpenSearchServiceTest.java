package org.example.evaluations.services;

import org.example.evaluations.evaluation.models.Flight;
import org.example.evaluations.evaluation.repos.FlightRepository;
import org.example.evaluations.evaluation.services.OpenSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.sql.Time;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OpenSearchServiceTest {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private OpenSearchService openSearchService;

    private final Integer pageSize = 5;
    private final Integer pageNumber = 0;

    @BeforeEach
    void setUp() {
        // Clear the repository before each test
        flightRepository.deleteAll();
        setupSampleData();
    }

    private void setupSampleData() {
        Flight flight1 = new Flight();
        flight1.setFlightId("FL123");
        flight1.setAirlinesName("Airline A");
        flight1.setSource("Source A");
        flight1.setDestination("Destination A");
        flight1.setTime(Time.valueOf("14:30:00"));
        flight1.setCaptainName("Captain A");
        flight1.setSeatsOccupied(10L);
        flight1.setSeatsVacant(150L);
        flight1.setStop("Source D");

        Flight flight2 = new Flight();
        flight2.setFlightId("FL124");
        flight2.setAirlinesName("Airline B");
        flight2.setSource("Source B");
        flight2.setDestination("Destination B");
        flight2.setTime(Time.valueOf("15:30:00"));
        flight2.setCaptainName("Captain B");
        flight2.setSeatsOccupied(5L);
        flight2.setSeatsVacant(145L);
        flight2.setStop("One Stop");

        Flight flight3 = new Flight();
        flight3.setFlightId("FL125");
        flight3.setAirlinesName("Airline C");
        flight3.setSource("Source C");
        flight3.setDestination("Source A");
        flight3.setTime(Time.valueOf("12:00:00"));
        flight3.setCaptainName("Captain C");
        flight3.setSeatsOccupied(100L);
        flight3.setSeatsVacant(50L);
        flight3.setStop("None");

        flightRepository.saveAll(Arrays.asList(flight1, flight2, flight3));
    }

    @Test
    public void testGetFlightsMatchingSearchQuery_WithTimeWindow() throws Exception {
        String query = "14:30:00";

        Page<Flight> flights = openSearchService.getFlightsMatchingSearchQuery(query, pageSize, pageNumber);

        assertEquals(3, flights.getContent().size());
    }

    @Test
    public void testGetFlightsMatchingSearchQuery_WithSource() throws Exception {

        String query = "Source A";

        Page<Flight> flights = openSearchService.getFlightsMatchingSearchQuery(query, pageSize, pageNumber);

        assertEquals(2, flights.getContent().size());
    }

    @Test
    public void testGetFlightsMatchingSearchQuery_WithAirlines() throws Exception {

        String query = "Airline B";

        Page<Flight> flights = openSearchService.getFlightsMatchingSearchQuery(query, pageSize, pageNumber);

        assertEquals(1, flights.getContent().size());
    }

    @Test
    public void testGetFlightsMatchingSearchQuery_WithStops() throws Exception {

        String query = "Source D";

        Page<Flight> flights = openSearchService.getFlightsMatchingSearchQuery(query, pageSize, pageNumber);

        assertEquals(1, flights.getContent().size());
    }

    @Test
    public void testGetFlightsMatchingSearchQuery_WithInvalidTime() throws Exception {
        String query = "08:30:00";

        Page<Flight> flights = openSearchService.getFlightsMatchingSearchQuery(query, pageSize, pageNumber);

        assertEquals(0, flights.getContent().size());
    }

    @Test
    public void testGetFlightsMatchingSearchQuery_WithFlightId() throws Exception {
        String query = "FL1%";

        Page<Flight> flights = openSearchService.getFlightsMatchingSearchQuery(query, pageSize, pageNumber);

        assertEquals(3, flights.getContent().size());
    }

    @Test
    public void testGetFlightsMatchingSearchQuery_UnknownQuery() throws Exception {
        String query = "Raj Shamani";

        Page<Flight> flights = openSearchService.getFlightsMatchingSearchQuery(query, pageSize, pageNumber);

        assertEquals(0, flights.getContent().size());
    }
}
