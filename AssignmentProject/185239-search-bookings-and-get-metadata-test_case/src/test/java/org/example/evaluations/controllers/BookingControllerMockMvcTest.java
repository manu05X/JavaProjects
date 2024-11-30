package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.BookingController;
import org.example.evaluations.evaluation.dtos.BookingSearchDto;
import org.example.evaluations.evaluation.models.Booking;
import org.example.evaluations.evaluation.services.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
public class BookingControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllBookingDoneByGuest() throws Exception {
        // Arrange
        BookingSearchDto searchDto = new BookingSearchDto();
        searchDto.setGuestEmail("guest@example.com");
        searchDto.setPageNumber(0);
        searchDto.setPageSize(5);

        Booking booking1 = createBooking();
        Booking booking2 = createBooking();
        List<Booking> bookings = Arrays.asList(booking1, booking2);
        Page<Booking> bookingPage = new PageImpl<>(bookings, PageRequest.of(0, 5), bookings.size());

        when(bookingService.getAllBookingDoneByGuest("guest@example.com", 0, 5)).thenReturn(bookingPage);

        // Act & Assert
        mockMvc.perform(post("/bookings/guestEmail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(booking1.getId().toString()))
                .andExpect(jsonPath("$.content[1].id").value(booking2.getId().toString()));
    }

    @Test
    public void testGetAllBookingsOnDateWhenMayorIsStaying() throws Exception {
        // Arrange
        BookingSearchDto searchDto = new BookingSearchDto();
        searchDto.setGuestFirstName("John");
        searchDto.setGuestLastName("Doe");
        searchDto.setPageNumber(0);
        searchDto.setPageSize(5);

        Booking booking1 = createBooking();
        List<Booking> bookings = Arrays.asList(booking1);
        Page<Booking> bookingPage = new PageImpl<>(bookings, PageRequest.of(0, 5), bookings.size());

        when(bookingService.getAllBookingsOnDateWhenMayorIsStaying("John", "Doe", 0, 5)).thenReturn(bookingPage);

        // Act & Assert
        mockMvc.perform(post("/bookings/guestName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(booking1.getId().toString()));
    }

    @Test
    public void testGetAllBookingsDoneForRoomOnParticularDate() throws Exception {
        // Arrange
        BookingSearchDto searchDto = new BookingSearchDto();
        searchDto.setRoomNumber(1L);
        searchDto.setBookingDate(new Date());
        searchDto.setPageNumber(0);
        searchDto.setPageSize(5);

        Booking booking1 = createBooking();
        List<Booking> bookings = Arrays.asList(booking1);
        Page<Booking> bookingPage = new PageImpl<>(bookings, PageRequest.of(0, 5), bookings.size());

        when(bookingService.getAllBookingsDoneForRoomOnParticularDate(1L, searchDto.getBookingDate(), 0, 5)).thenReturn(bookingPage);

        // Act & Assert
        mockMvc.perform(post("/bookings/roomNumber_date")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(booking1.getId().toString()));
    }

    private Booking createBooking() {
        Booking booking = new Booking();
        booking.setId(UUID.randomUUID());
        booking.setDate(new Date());
        return booking;
    }
}
