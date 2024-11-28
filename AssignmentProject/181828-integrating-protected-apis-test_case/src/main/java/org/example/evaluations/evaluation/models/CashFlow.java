package org.example.evaluations.evaluation.models;

import lombok.Data;

@Data
public class CashFlow {
    private Long year;
    private Long quarter;
    private String currency;
    private Long net_income;
    private Long free_cash_flow;
}
