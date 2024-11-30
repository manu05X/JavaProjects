package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Booking;
import org.example.evaluations.evaluation.models.Guest;
import org.example.evaluations.evaluation.models.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
       Page<Booking> findBookingsByGuest(Guest guest, Pageable pageable);

       List<Booking> findBookingsByGuestFirstNameAndGuestLastName(String firstName, String lastName);

       Page<Booking> findBookingByDate(Date date, Pageable pageable);

       Page<Booking> findBookingByRoomsAndDate(Room room, Date date, Pageable pageable);
}
