package org.example.evaluations.evaluation.dtos;

import lombok.Data;
import org.example.evaluations.evaluation.models.CashFlow;

import java.util.List;

@Data
public class RealTimeCashFlowData {
    private List<CashFlow> cash_flow;
}
