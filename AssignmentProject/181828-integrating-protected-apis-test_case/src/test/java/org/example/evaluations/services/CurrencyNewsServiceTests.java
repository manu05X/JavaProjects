package org.example.evaluations.services;

import org.example.evaluations.evaluation.services.CurrencyNewsService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import org.example.evaluations.evaluation.dtos.RealTimeNewsData;
import org.example.evaluations.evaluation.dtos.RealTimeNewsResult;
import org.example.evaluations.evaluation.models.News;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Test;


@SpringBootTest
public class CurrencyNewsServiceTests {

    @Autowired
    private CurrencyNewsService currencyNewsService;

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }

    @Test
    public void testGetCurrencyNews() {
        // Arrange
        String fromCurrency = "USD";
        String toCurrency = "EUR";
        News news1 = new News();
        news1.setArticle_title("Title1");
        news1.setSource("Source1");
        News news2 = new News();
        news2.setArticle_title("Title2");
        news2.setSource("Source2");
        RealTimeNewsData realTimeNewsData = new RealTimeNewsData();
        List<News> list = new ArrayList<>();
        list.add(news1);
        list.add(news2);
        realTimeNewsData.setNews(list);
        RealTimeNewsResult realTimeNewsResult = new RealTimeNewsResult();
        realTimeNewsResult.setData(realTimeNewsData);
        ResponseEntity<RealTimeNewsResult> responseEntity = ResponseEntity.ok(realTimeNewsResult);

        when(restTemplate.exchange(
                eq("https://real-time-finance-data.p.rapidapi.com/currency-news?from_symbol={currency1}&to_symbol={currency2}"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(RealTimeNewsResult.class),
                eq(fromCurrency),
                eq(toCurrency)
        )).thenReturn(responseEntity);

        // Act
        List<News> result = currencyNewsService.getCurrencyNews(fromCurrency, toCurrency);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).getArticle_title());
        assertEquals("Source1", result.get(0).getSource());
        assertEquals("Title2", result.get(1).getArticle_title());
        assertEquals("Source2", result.get(1).getSource());
    }



}
