package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.UserLocationDto;
import org.example.evaluations.evaluation.models.Restaurant;
import org.example.evaluations.evaluation.services.NearbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nearby/restaurants")
public class NearbyController {

    @Autowired
    private NearbyService nearbyService;


    @PostMapping("/add")
    public Boolean addLocation(@RequestBody Restaurant restaurant) {
        return nearbyService.addLocation(restaurant);
    }

    @PostMapping
    public List<Restaurant> findAllNearbyRestaurants(@RequestBody UserLocationDto userLocationDto) {
       return nearbyService.findNearbyLocations(userLocationDto.getLatitude(), userLocationDto.getLongitude(), userLocationDto.getRadius());
    }
}
