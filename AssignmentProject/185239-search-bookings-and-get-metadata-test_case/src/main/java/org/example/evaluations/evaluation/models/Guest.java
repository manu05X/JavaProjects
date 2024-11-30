package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Guest {

    @Id
    private String emailId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    @OneToMany(mappedBy = "guest")
    private List<Booking> bookings;
}
