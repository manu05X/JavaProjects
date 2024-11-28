package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.BookingController;
import org.example.evaluations.evaluation.dtos.BookingUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetBookingById() throws Exception {
        Long inventoryId = 1L;

        mockMvc.perform(get("/booking/{bookingId}", inventoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.guestName").value("Your Name"))
                .andExpect(jsonPath("$.date").isNotEmpty());
    }

    @Test
    public void testGetBookingByGuestNameAndDate() throws Exception {
        String guestName = "anuragkhanna";

        mockMvc.perform(get("/booking/guest/anuragkhanna/date/2024-08-15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(99999))
                .andExpect(jsonPath("$.guestName").value(guestName))
                .andExpect(jsonPath("$.date").value("2024-08-15T00:00:00.000+00:00"));
    }

    @Test
    public void testListBookingsOfParticularDate() throws Exception {
        mockMvc.perform(get("/booking/date/2024-08-15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].guestName").value("Your Name"))
                .andExpect(jsonPath("$[0].id").value(99999))
                .andExpect(jsonPath("$[0].date").value("2024-08-15T00:00:00.000+00:00"));
    }

    @Test
    public void testListBookingsOfParticularDate_invalidDateFormat() throws Exception {
        mockMvc.perform(get("/booking/date/15augt2024"))
                .andExpect(status().isOk())
                .andExpect(content().string(emptyOrNullString()));
    }

    @Test
    public void testGetBookingByGuestNameAndDate_invalidDateFormat() throws Exception {
        mockMvc.perform(get("/booking/guest/anuragkhanna/date/invalid-date-format"))
                .andExpect(status().isOk())
                .andExpect(content().string(emptyOrNullString()));
    }

    @Test
    public void testUpdateBooking() throws Exception {
        BookingUpdateRequestDto requestDto = new BookingUpdateRequestDto();
        requestDto.setGuestName("Anurag Khanna");
        requestDto.setDate("2024-08-15");
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        //requestDto.setDate(simpleDateFormat.parse("2024-08-15"));

        mockMvc.perform(put("/booking/2")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.guestName").value("Anurag Khanna"))
                .andExpect(jsonPath("$.date").value("2024-08-15T00:00:00.000+00:00"));
    }
}
