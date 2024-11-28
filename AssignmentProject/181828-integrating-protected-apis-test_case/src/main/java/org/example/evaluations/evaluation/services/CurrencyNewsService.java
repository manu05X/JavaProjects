package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.RealTimeNewsData;
import org.example.evaluations.evaluation.dtos.RealTimeNewsResult;
import org.example.evaluations.evaluation.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CurrencyNewsService implements ICurrencyService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private static final String key = "x-rapidapi-key";
    private static final String value = "650b8aea0cmshc580a2cefb4df87p172f51jsnc0e061860c02";

    public List<News> getCurrencyNews(String fromCurrency,String toCurrency) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.set(key,value);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RealTimeNewsResult realTimeNewsResult = restTemplate.exchange("https://real-time-finance-data.p.rapidapi.com/currency-news?from_symbol={currency1}&to_symbol={currency2}", HttpMethod.GET,entity, RealTimeNewsResult.class,fromCurrency,toCurrency).getBody();
        RealTimeNewsData realTimeNewsData = realTimeNewsResult.getData();
        return realTimeNewsData.getNews();
    }


}
