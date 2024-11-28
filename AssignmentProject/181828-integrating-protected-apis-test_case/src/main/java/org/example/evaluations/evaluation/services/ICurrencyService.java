package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.News;

import java.util.List;

public interface ICurrencyService {
    List<News> getCurrencyNews(String fromCurrency, String toCurrency);
}
