package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.BookingRequestDto;
import org.example.evaluations.evaluation.dtos.BookingResponseDto;
import org.example.evaluations.evaluation.services.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @GetMapping("/booking/guest/{guestEmail}")
    public List<BookingResponseDto> getAllBookingsForGuest(@PathVariable String guestEmail) {
          return bookingService.getAllBookingsPerGuest(guestEmail);
    }

    @GetMapping("/booking/{bookingId}")
    public BookingResponseDto getBooking(@PathVariable Long bookingId) {
        return bookingService.getBooking(bookingId);
    }


    @PutMapping("/booking/{bookingId}")
    public BookingResponseDto replaceBooking(@PathVariable Long bookingId, @RequestBody BookingRequestDto bookingRequestDto) {
       return bookingService.replaceBooking(bookingId,bookingRequestDto);
    }

    @PostMapping("/booking")
    public BookingResponseDto createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
      return bookingService.createBooking(bookingRequestDto);
    }

    @DeleteMapping("/booking/{bookingId}")
    public Boolean deleteBooking(@PathVariable Long bookingId) {
      return bookingService.deleteBooking(bookingId);
    }
}


/*{
        "customerName" : "abcde",
        "customerEmail" : "defgh",
        "checkInDate" : "2024-10-12",
        "checkOutDate" : "2024-10-20",
        "roomRequestDtos" : [{
        "roomType" : "SUITE",
        "roomCount" : 10
        },{
        "roomType" : "DELUXE",
        "roomCount" : 20
        }]
 }
        */