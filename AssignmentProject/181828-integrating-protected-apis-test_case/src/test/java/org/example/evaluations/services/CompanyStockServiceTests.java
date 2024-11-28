package org.example.evaluations.services;

import org.example.evaluations.evaluation.dtos.RealTimeCashFlowData;
import org.example.evaluations.evaluation.dtos.RealTimeCashFlowResult;
import org.example.evaluations.evaluation.dtos.RealTimeNewsData;
import org.example.evaluations.evaluation.dtos.RealTimeNewsResult;
import org.example.evaluations.evaluation.models.CashFlow;
import org.example.evaluations.evaluation.models.News;
import org.example.evaluations.evaluation.services.CompanyStockService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class CompanyStockServiceTests {

    @Autowired
    private CompanyStockService companyStockService;

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }

    @Test
    public void testGetStockNews() {
        // Arrange
        String symbol = "AAPL";
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
                eq("https://real-time-finance-data.p.rapidapi.com/stock-news?symbol={symbol}"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(RealTimeNewsResult.class),
                eq(symbol)
        )).thenReturn(responseEntity);

        // Act
        List<News> result = companyStockService.getStockNews(symbol);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).getArticle_title());
        assertEquals("Source1", result.get(0).getSource());
        assertEquals("Title2", result.get(1).getArticle_title());
        assertEquals("Source2", result.get(1).getSource());
    }

    @Test
    public void testGetCompanyCashFlow() {
        // Arrange
        String symbol = "AAPL";
        CashFlow cashFlow1 = new CashFlow();
        cashFlow1.setQuarter(1L);
        cashFlow1.setFree_cash_flow(100000L);
        CashFlow cashFlow2 = new CashFlow();
        cashFlow2.setQuarter(3L);
        cashFlow2.setFree_cash_flow(15000000L);
        RealTimeCashFlowData realTimeCashFlowData = new RealTimeCashFlowData();
        List<CashFlow> cashFlows = new ArrayList<>();
        cashFlows.add(cashFlow1);
        cashFlows.add(cashFlow2);
        realTimeCashFlowData.setCash_flow(cashFlows);
        RealTimeCashFlowResult realTimeCashFlowResult = new RealTimeCashFlowResult();
        realTimeCashFlowResult.setData(realTimeCashFlowData);
        ResponseEntity<RealTimeCashFlowResult> responseEntity = ResponseEntity.ok(realTimeCashFlowResult);

        when(restTemplate.exchange(
                eq("https://real-time-finance-data.p.rapidapi.com/company-cash-flow?symbol={symbol}"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(RealTimeCashFlowResult.class),
                eq(symbol)
        )).thenReturn(responseEntity);

        // Act
        List<CashFlow> result = companyStockService.getCompanyCashFlow(symbol);

        // Assert
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getQuarter());
        assertEquals(100000, result.get(0).getFree_cash_flow());
        assertEquals(3, result.get(1).getQuarter());
        assertEquals(15000000, result.get(1).getFree_cash_flow());
    }
}
