package org.example.evaluations.evaluation.services;

import org.antlr.v4.runtime.misc.Pair;
import org.example.evaluations.evaluation.models.Flight;
import org.example.evaluations.evaluation.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class OpenSearchService {

    @Autowired
    private FlightRepository flightRepository;

    public Page<Flight> getFlightsMatchingSearchQuery(String query, Integer pageSize, Integer pageNumber) {
        try {
            Time time = Time.valueOf(query);
            Pair<Time, Time> timeWindow = getTimeWindow(time);
            List<Flight> flightsMatchedByTime = flightRepository.findFlightByTimeBetween(timeWindow.a, timeWindow.b);
            List<List<Flight>> flightEntriesMatchingDifferentCriteria = new ArrayList<>();
            flightEntriesMatchingDifferentCriteria.add(flightsMatchedByTime);
            return mergeListsIntoPage(flightEntriesMatchingDifferentCriteria, PageRequest.of(pageNumber, pageSize));
        }catch (IllegalArgumentException exception) {

            List<Flight> flightsMatchedByAirlinesName = flightRepository.findFlightByAirlinesName(query);
            List<Flight> flightsMatchedByFlightId = flightRepository.findFlightByFlightIdLike(query);
            List<Flight> flightsMatchedBySource = flightRepository.findFlightBySourceLike(query);
            List<Flight> flightsMatchByDestination = flightRepository.findFlightByDestinationLike(query);
            List<Flight> flightsMatchedByStop = flightRepository.findFlightByStopLike(query);

            List<List<Flight>> flightEntriesMatchingDifferentCriteria = new ArrayList<>();
            flightEntriesMatchingDifferentCriteria.add(flightsMatchedByAirlinesName);
            flightEntriesMatchingDifferentCriteria.add(flightsMatchedBySource);
            flightEntriesMatchingDifferentCriteria.add(flightsMatchByDestination);
            flightEntriesMatchingDifferentCriteria.add(flightsMatchedByStop);
            flightEntriesMatchingDifferentCriteria.add(flightsMatchedByFlightId);
            return mergeListsIntoPage(flightEntriesMatchingDifferentCriteria, PageRequest.of(pageNumber, pageSize));
        }
    }

    private Pair<Time,Time> getTimeWindow(Time time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        calendar.add(Calendar.HOUR_OF_DAY, -3);
        Time startTime = new Time(calendar.getTimeInMillis());

        calendar.add(Calendar.HOUR_OF_DAY, 6);
        Time endTime = new Time(calendar.getTimeInMillis());

        return new Pair<>(startTime,endTime);
    }

    private static <T> Page<T> mergeListsIntoPage(List<List<T>> lists, Pageable pageable) {
        List<T> mergedContent = new ArrayList<>();

        for (List<T> list : lists) {
            if (list != null) {
                mergedContent.addAll(list);
            }
        }

        return new PageImpl<>(mergedContent, pageable, mergedContent.size());
    }
}
