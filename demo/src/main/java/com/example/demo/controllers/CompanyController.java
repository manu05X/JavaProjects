package com.example.demo.controllers;

import com.example.demo.models.CashFlow;
import com.example.demo.models.News;
import com.example.demo.services.CompanyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    private CompanyStockService companyService;

    //Add your APIs here

    @GetMapping("/company/stockNews")
    public List<News> getStockNews(@RequestParam String symbol){
        return companyService.getStockNews(symbol);
    }

    // Endpoint to fetch company cash flow for a given symbol
    @GetMapping("/company/cashFlow")
    public List<CashFlow> getCompanyCashFlow(@RequestParam String symbol) {
        //return companyService.getCompanyCashFlow(symbol);
        return companyService.getCompanyCashFlow(symbol);
    }
}
