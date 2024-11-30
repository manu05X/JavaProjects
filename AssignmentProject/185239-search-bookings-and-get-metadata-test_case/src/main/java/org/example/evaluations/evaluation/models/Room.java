package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Room {
    @Id
    private Long id;

    private RoomType roomType;

    @ManyToMany(mappedBy = "rooms")
    private List<Booking> bookings;
}
