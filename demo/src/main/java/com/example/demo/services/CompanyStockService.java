package com.example.demo.services;

import com.example.demo.dtos.RealTimeCashFlowResult;
import com.example.demo.dtos.RealTimeNewsResult;
import com.example.demo.models.CashFlow;
import com.example.demo.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyStockService implements IStockService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    // API key for RapidAPI (ensure you replace this with a valid key)
    private static final String RAPID_API_KEY = "8f65cd8c1emsh626c703d36fa8ebp1be77cjsn34dba67f3e98";
    private static final String BASE_URL = "https://real-time-finance-data.p.rapidapi.com";


    public List<News> getStockNews(String symbol) {
        //Add your implementation here
        String url = "https://real-time-finance-data.p.rapidapi.com/stock-news?symbol=" + symbol;

        // Set up headers with X-RapidAPI-Key
        HttpHeaders serviceHeaders = new HttpHeaders();
        serviceHeaders.set("X-RapidAPI-Key", "8f65cd8c1emsh626c703d36fa8ebp1be77cjsn34dba67f3e98");
        serviceHeaders.setContentType(MediaType.APPLICATION_JSON);

        // Create HttpEntity with headers
        HttpEntity<String> entity = new HttpEntity<>(serviceHeaders);

        // Make the HTTP GET request
        ResponseEntity<RealTimeNewsResult> response = restTemplateBuilder.build().exchange(
                url, HttpMethod.GET, entity, RealTimeNewsResult.class);

        // Check if the response is successful
        if(response != null && response.getStatusCode() == HttpStatus.OK){
            System.out.println("dadad" + response.getBody().toString());
            return response.getBody().getData().getNews();
        } else {
            throw new RuntimeException("Failed to fetch stock news");
        }

    }

    public List<CashFlow> getCompanyCashFlow(String symbol) {
        //Add your implementation here
        String url = BASE_URL+"/company-cash-flow?symbol="+symbol;

        // Set up headers with X-RapidAPI-Key
        HttpHeaders serviceHeaders = new HttpHeaders();
        serviceHeaders.set("X-RapidAPI-Key", RAPID_API_KEY);
        serviceHeaders.setContentType(MediaType.APPLICATION_JSON);

        // Create HttpEntity with headers
        HttpEntity<String> entity = new HttpEntity<>(serviceHeaders);

        // Make the HTTP GET request
        ResponseEntity<RealTimeCashFlowResult> response = restTemplateBuilder.build().exchange(
                url, HttpMethod.GET, entity, RealTimeCashFlowResult.class);

        // Check if the response is successful
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getData().getCash_flow();
        } else {
            throw new RuntimeException("Failed to fetch company cash flow");
        }
    }
}

