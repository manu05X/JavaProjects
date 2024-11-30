package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Booking {

    @Id
    private UUID id;

    private Date date;

    @ManyToOne
    private Guest guest;

    @ManyToMany
    private List<Room> rooms;
}
