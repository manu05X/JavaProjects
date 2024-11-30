package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.Booking;
import org.example.evaluations.evaluation.models.Guest;
import org.example.evaluations.evaluation.models.Room;
import org.example.evaluations.evaluation.repos.BookingRepository;
import org.example.evaluations.evaluation.repos.GuestRepository;
import org.example.evaluations.evaluation.repos.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;


    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Page<Booking> getAllBookingDoneByGuest(String guestEmail, Integer pageNumber, Integer pageSize) {
        Optional<Guest> guestOptional = guestRepository.findById(guestEmail);
        if(guestOptional.isEmpty()) return  null;

        return bookingRepository.findBookingsByGuest(guestOptional.get(), PageRequest.of(pageNumber,pageSize));
    }


    // In case Mayor is having multiple bookings, pick first entry
    public Page<Booking> getAllBookingsOnDateWhenMayorIsStaying(String firstName, String lastName, Integer pageNumber, Integer pageSize) {
         List<Booking> bookings = bookingRepository.findBookingsByGuestFirstNameAndGuestLastName(firstName,lastName);
         Booking booking = bookings.get(0);
         return  bookingRepository.findBookingByDate(booking.getDate(),PageRequest.of(pageNumber,pageSize));
    }

    public Page<Booking> getAllBookingsDoneForRoomOnParticularDate(Long roomNumber, Date date, Integer pageNumber, Integer pageSize) {
          Optional<Room> roomOptional = roomRepository.findById(roomNumber);
          if (roomOptional.isEmpty()) return null;

          return bookingRepository.findBookingByRoomsAndDate(roomOptional.get(), date, PageRequest.of(pageNumber,pageSize));
    }
}
