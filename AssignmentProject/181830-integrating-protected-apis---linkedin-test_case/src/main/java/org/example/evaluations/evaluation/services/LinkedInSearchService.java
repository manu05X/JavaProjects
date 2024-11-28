package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.LinkedInSearchData;
import org.example.evaluations.evaluation.dtos.LinkedInSearchRequest;
import org.example.evaluations.evaluation.dtos.LinkedInSearchResult;
import org.example.evaluations.evaluation.models.LinkedInSearchItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class LinkedInSearchService implements ISearchService {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    private static final String key = "x-rapidapi-key";
    private static final String value = "288e79b632msh1120dc7e5d68b40p1d0271jsn1f0f482b0d88";

    //Reference - https://binarycoders.wordpress.com/2020/10/04/add-a-header-to-spring-resttemplate/#:~:text=add(%20new%20HeaderRequestInterceptor(%20%22X,setInterceptors(interceptors)%3B
    public List<LinkedInSearchItem> searchPeople(LinkedInSearchRequest linkedInSearchRequest) {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new HeaderRequestInterceptor(key, value));
        interceptors.add(new HeaderRequestInterceptor("Content-Type","application/json"));
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.setInterceptors(interceptors);
        ResponseEntity<LinkedInSearchResult> linkedInSearchResultResponseEntity = restTemplate.postForEntity("https://linkedin-data-api.p.rapidapi.com/search-people-by-url", linkedInSearchRequest, LinkedInSearchResult.class);
        LinkedInSearchData linkedInSearchData = linkedInSearchResultResponseEntity.getBody().getData();
        return linkedInSearchData.getItems();
    }
}
