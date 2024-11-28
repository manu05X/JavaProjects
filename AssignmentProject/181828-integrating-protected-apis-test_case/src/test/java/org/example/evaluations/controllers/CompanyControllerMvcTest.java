package org.example.evaluations.controllers;

import org.example.evaluations.evaluation.controllers.CompanyController;
import org.example.evaluations.evaluation.models.CashFlow;
import org.example.evaluations.evaluation.models.News;
import org.example.evaluations.evaluation.services.IStockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
public class CompanyControllerMvcTest {

    @MockBean
    private IStockService companyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetStockNews() throws Exception {
        // Arrange
        String symbol = "AAPL";
        News news1 = new News();
        news1.setArticle_title("Title1");
        news1.setSource("Source1");
        News news2 = new News();
        news2.setArticle_title("Title2");
        news2.setSource("Source2");
        List<News> newsList = Arrays.asList(news1, news2);

        when(companyService.getStockNews(symbol)).thenReturn(newsList);

        // Act & Assert
        mockMvc.perform(get("/company/stockNews")
                        .param("symbol", symbol))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].article_title").value("Title1"))
                .andExpect(jsonPath("$[0].source").value("Source1"))
                .andExpect(jsonPath("$[1].article_title").value("Title2"))
                .andExpect(jsonPath("$[1].source").value("Source2"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetCompanyCashFlow() throws Exception {
        // Arrange
        String symbol = "AAPL";
        CashFlow cashFlow1 = new CashFlow();
        cashFlow1.setQuarter(1L);
        cashFlow1.setFree_cash_flow(100000L);
        CashFlow cashFlow2 = new CashFlow();
        cashFlow2.setQuarter(3L);
        cashFlow2.setFree_cash_flow(15000000L);
        List<CashFlow> cashFlowList = Arrays.asList(cashFlow1, cashFlow2);

        when(companyService.getCompanyCashFlow(symbol)).thenReturn(cashFlowList);

        // Act & Assert
        mockMvc.perform(get("/company/cashFlow")
                        .param("symbol", symbol))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].quarter").value(1))
                .andExpect(jsonPath("$[0].free_cash_flow").value(100000))
                .andExpect(jsonPath("$[1].quarter").value(3))
                .andExpect(jsonPath("$[1].free_cash_flow").value(15000000))
                .andDo(MockMvcResultHandlers.print());
    }
}
