package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.BookingRequestDto;
import org.example.evaluations.evaluation.dtos.BookingResponseDto;

import java.util.List;

public interface IBookingService {
    BookingResponseDto getBooking(Long bookingId);

    List<BookingResponseDto> getAllBookingsPerGuest(String userEmail);

    BookingResponseDto replaceBooking(Long bookingId, BookingRequestDto bookingRequestDto);

    BookingResponseDto createBooking(BookingRequestDto bookingRequestDto);

    Boolean deleteBooking(Long bookingId);
}
