package org.example.evaluations.controllers;

import org.example.evaluations.evaluation.controllers.CurrencyController;
import org.example.evaluations.evaluation.models.News;
import org.example.evaluations.evaluation.services.ICurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyController.class)
public class CurrencyControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICurrencyService currencyService;

    @Test
    public void testGetCurrencyNews() throws Exception {
        // Arrange
        String fromSymbol = "USD";
        String toSymbol = "EUR";
        News news1 = new News();
        news1.setArticle_title("Title1");
        news1.setSource("Source1");
        News news2 = new News();
        news2.setArticle_title("Title2");
        news2.setSource("Source2");

        List<News> newsList = new ArrayList<>();
        newsList.add(news1);
        newsList.add(news2);

        when(currencyService.getCurrencyNews(fromSymbol, toSymbol)).thenReturn(newsList);

        // Act & Assert
        mockMvc.perform(get("/currency/conversionNews")
                        .param("from_symbol", fromSymbol)
                        .param("to_symbol", toSymbol))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].article_title").value("Title1"))
                .andExpect(jsonPath("$[0].source").value("Source1"))
                .andExpect(jsonPath("$[1].article_title").value("Title2"))
                .andExpect(jsonPath("$[1].source").value("Source2"))
                .andDo(MockMvcResultHandlers.print());
    }
}
