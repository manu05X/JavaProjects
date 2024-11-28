package com.example.demo.controllers;

import com.example.demo.models.News;
import com.example.demo.services.ICurrencyService;
import com.example.demo.services.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {
    @Autowired
    private ICurrencyService currencyService;

    //Add your APIs here
    @GetMapping("/currency/conversionNews")
    public List<News> getCurrencyNews(@RequestParam String from_symbol, @RequestParam String to_symbol){
        return currencyService.getCurrencyNews(from_symbol, to_symbol);
    }
}
