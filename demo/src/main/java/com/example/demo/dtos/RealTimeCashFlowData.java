package com.example.demo.dtos;

import com.example.demo.models.CashFlow;

import java.util.List;
import lombok.Data;

@Data
public class RealTimeCashFlowData {
    private List<CashFlow> cash_flow;
}
