package org.example.evaluations.evaluation.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Entity
@Setter
@Getter
public class Flight {
    @Id
    private String flightId;

    private String airlinesName;

    private String source;

    private String destination;

    private String stop;

    private Time time;

    private String captainName;

    private Long seatsOccupied;

    private Long seatsVacant;

    private Size size;
}
