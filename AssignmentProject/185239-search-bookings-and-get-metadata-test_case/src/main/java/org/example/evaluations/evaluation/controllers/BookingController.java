package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.BookingSearchDto;
import org.example.evaluations.evaluation.models.Booking;
import org.example.evaluations.evaluation.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("bookings/guestEmail")
    public Page<Booking> getAllBookingDoneByGuest(@RequestBody BookingSearchDto bookingSearchDto) {
         return bookingService.getAllBookingDoneByGuest(bookingSearchDto.getGuestEmail(),bookingSearchDto.getPageNumber(), bookingSearchDto.getPageSize());
     }


     @PostMapping("bookings/guestName")
    public Page<Booking> getAllBookingsOnDateWhenMayorIsStaying(@RequestBody BookingSearchDto bookingSearchDto) {
         return bookingService.getAllBookingsOnDateWhenMayorIsStaying(bookingSearchDto.getGuestFirstName(), bookingSearchDto.getGuestLastName(), bookingSearchDto.getPageNumber(), bookingSearchDto.getPageSize());
     }


     @PostMapping("bookings/roomNumber_date")
    public Page<Booking> getAllBookingsDoneForRoomOnParticularDate(@RequestBody BookingSearchDto bookingSearchDto) {
        return bookingService.getAllBookingsDoneForRoomOnParticularDate(bookingSearchDto.getRoomNumber(), bookingSearchDto.getBookingDate(), bookingSearchDto.getPageNumber(), bookingSearchDto.getPageSize());
     }
}
