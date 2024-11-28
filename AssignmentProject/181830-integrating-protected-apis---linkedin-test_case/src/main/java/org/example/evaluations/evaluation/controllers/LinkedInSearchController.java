package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.LinkedInSearchRequest;
import org.example.evaluations.evaluation.models.LinkedInSearchItem;
import org.example.evaluations.evaluation.services.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/linkedInSearch")
public class LinkedInSearchController {

    @Autowired
    private ISearchService searchService;

    @PostMapping
    public List<LinkedInSearchItem> searchPeopleByUrl(@RequestBody LinkedInSearchRequest linkedInSearchRequest) {
        return searchService.searchPeople(linkedInSearchRequest);
    }
}


/*
{
    "url" : "https://www.linkedin.com/search/results/people/?currentCompany=%5B%221035%22%5D&geoUrn=%5B%22103644278%22%5D&keywords=max&origin=FACETED_SEARCH&sid=%3AB5\"}"
}
 */
