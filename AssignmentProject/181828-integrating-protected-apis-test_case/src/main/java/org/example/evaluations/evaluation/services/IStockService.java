package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.models.CashFlow;
import org.example.evaluations.evaluation.models.News;

import java.util.List;

public interface IStockService {
    List<News> getStockNews(String symbol);
    List<CashFlow> getCompanyCashFlow(String symbol);
}
