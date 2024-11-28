package com.example.demo.services;

import com.example.demo.dtos.RealTimeNewsResult;
import com.example.demo.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyNewsService implements ICurrencyService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    // API key for RapidAPI (ensure you replace this with a valid key)
    private static final String RAPID_API_KEY = "8f65cd8c1emsh626c703d36fa8ebp1be77cjsn34dba67f3e98";
    private static final String BASE_URL = "https://real-time-finance-data.p.rapidapi.com";


    public List<News> getCurrencyNews(String fromCurrency, String toCurrency) {
        //Add your implementation here
        String url = BASE_URL+"/currency-news?from_symbol="+fromCurrency+"&to_symbol="+toCurrency;

        HttpHeaders serviceHeaders = new HttpHeaders();
        serviceHeaders.set("X-RapidAPI-Key", RAPID_API_KEY);
        serviceHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(serviceHeaders);

        ResponseEntity<RealTimeNewsResult> response = restTemplateBuilder.build().exchange(
                url, HttpMethod.GET, entity, RealTimeNewsResult.class);

        if (response == null || response.getBody() == null) {
            throw new RuntimeException("API returned a null response");
        }

        if(response.getStatusCode() == HttpStatus.OK){
            return response.getBody().getData().getNews();
        } else {
            throw new RuntimeException("Failed to fetch currency news");
        }
    }
}
