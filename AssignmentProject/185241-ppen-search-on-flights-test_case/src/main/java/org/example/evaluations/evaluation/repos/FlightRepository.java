package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;


@Repository
public interface FlightRepository extends JpaRepository<Flight,String> {

    List<Flight> findFlightByAirlinesName(String airlinesName);

    List<Flight> findFlightByFlightIdLike(String flightId);

    List<Flight> findFlightBySourceLike(String source);

    List<Flight> findFlightByDestinationLike(String destination);

    List<Flight> findFlightByStopLike(String stop);

    List<Flight> findFlightByTimeBetween(Time time1, Time time2);
}
