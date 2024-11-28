package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.models.News;
import org.example.evaluations.evaluation.services.ICurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private ICurrencyService currencyService;

    //localhost:8080/currency_/conversionNews?from_symbol=USD&to_symbol=INR
    @GetMapping("/conversionNews")
    public List<News> getCurrencyNews(@RequestParam(required = true) String from_symbol, @RequestParam(required = true) String to_symbol) {
        return currencyService.getCurrencyNews(from_symbol,to_symbol);
    }
}
