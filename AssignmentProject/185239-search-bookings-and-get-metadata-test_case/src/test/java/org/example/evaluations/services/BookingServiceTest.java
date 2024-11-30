package org.example.evaluations.services;

import org.example.evaluations.evaluation.models.Booking;
import org.example.evaluations.evaluation.models.Guest;
import org.example.evaluations.evaluation.models.Room;
import org.example.evaluations.evaluation.models.RoomType;
import org.example.evaluations.evaluation.repos.BookingRepository;
import org.example.evaluations.evaluation.repos.GuestRepository;
import org.example.evaluations.evaluation.repos.RoomRepository;
import org.example.evaluations.evaluation.services.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private GuestRepository guestRepository;


    @Test
    public void testGetAllBookingDoneByGuest() {
        // Arrange
        String guestEmail = "guest@example.com";
        Integer pageNumber = 0;
        Integer pageSize = 5;

        Guest guest = createGuest(guestEmail,"John","Doe");
        guestRepository.save(guest);

        Booking booking1 = createBooking(guest,new Date(2024,10,5));
        Booking booking2 = createBooking(guest,new Date(2024,12,25));
        bookingRepository.save(booking1);
        bookingRepository.save(booking2);

        // Act
        Page<Booking> result = bookingService.getAllBookingDoneByGuest(guestEmail, pageNumber, pageSize);

        // Assert
        assertEquals(2, result.getContent().size());
        assertEquals(booking1.getId(), result.getContent().get(0).getId());
    }

    @Test
    public void testGetAllBookingsOnDateWhenMayorIsStaying() {
        // Arrange
        String firstName = "Harry";
        String lastName = "Minson";
        Integer pageNumber = 0;
        Integer pageSize = 5;

        Guest guest = createGuest("mayor@example.com",firstName,lastName);
        Booking booking = createBooking(guest,new Date(2024,12,25));
        guestRepository.save(guest);
        bookingRepository.save(booking);

        // Act
        Page<Booking> result = bookingService.getAllBookingsOnDateWhenMayorIsStaying(firstName, lastName, pageNumber, pageSize);

        // Assert
        assertEquals(2, result.getContent().size());
        assertEquals(booking.getId(), result.getContent().get(1).getId());
    }

    @Test
    public void testGetAllBookingsDoneForRoomOnParticularDate() {
        // Arrange
        Long roomNumber = 3L;
        Date date = new Date(2024,11,13);
        Integer pageNumber = 0;
        Integer pageSize = 5;

        Room room = createRoom(roomNumber);
        roomRepository.save(room);
        Booking booking = createBooking(null,date);
        booking.setRooms(Arrays.asList(room));
        bookingRepository.save(booking);

        // Act
        Page<Booking> result = bookingService.getAllBookingsDoneForRoomOnParticularDate(roomNumber, date, pageNumber, pageSize);

        // Assert
        assertEquals(1, result.getContent().size());
        assertEquals(booking.getId(), result.getContent().get(0).getId());
    }

    private Guest createGuest(String emailId,String firstName,String lastName) {
        Guest guest = new Guest();
        guest.setEmailId(emailId);
        guest.setFirstName(firstName);
        guest.setLastName(lastName);
        return guest;
    }

    private Booking createBooking(Guest guest,Date date) {
        Booking booking = new Booking();
        booking.setId(UUID.randomUUID());
        booking.setDate(date);
        booking.setGuest(guest);
        return booking;
    }

    private Room createRoom(Long id) {
        Room room = new Room();
        room.setId(id);
        room.setRoomType(RoomType.DELUXE);
        return room;
    }
}
