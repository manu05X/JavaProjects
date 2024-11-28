package com.example.demo.services;

import com.example.demo.models.CashFlow;
import com.example.demo.models.News;

import java.util.List;

public interface IStockService {
    List<News> getStockNews(String symbol);
    List<CashFlow> getCompanyCashFlow(String symbol);
}
