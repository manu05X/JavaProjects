package org.example.evaluations.evaluation.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Guest guest;

    private Double totalBill;

    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Room> rooms;

    private Date checkInDate;

    private Date checkOutDate;
}
