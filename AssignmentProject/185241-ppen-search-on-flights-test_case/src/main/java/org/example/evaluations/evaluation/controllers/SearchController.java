package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.models.Flight;
import org.example.evaluations.evaluation.services.OpenSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
public class SearchController {

    @Autowired
    private OpenSearchService openSearchService;

    @GetMapping("/search/{query}")
    public Page<Flight> getFlightsMatchingSearchQuery(@PathVariable String query, @RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        return openSearchService.getFlightsMatchingSearchQuery(query,pageSize,pageNumber);
    }
}
