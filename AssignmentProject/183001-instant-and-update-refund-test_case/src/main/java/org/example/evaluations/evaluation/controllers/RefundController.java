package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.RefundRequestDto;
import org.example.evaluations.evaluation.services.IRefundService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RefundController {

    @Autowired
    private IRefundService refundService;

    @PostMapping("/issueRefund")
    public String issueRefund(@RequestBody RefundRequestDto refundRequestDto) {
      return refundService.issueRefund(refundRequestDto.getAmount(), refundRequestDto.getReceipt());
    }

    @PatchMapping("/updateRefund/{refundId}")
    public String updateRefund(@PathVariable String refundId, @RequestBody RefundRequestDto refundRequestDto) {
        JSONObject jsonObject = new JSONObject();
        if (refundRequestDto != null) {
            if (refundRequestDto.getAmount() != null) {
                jsonObject.put("amount", refundRequestDto.getAmount());
            }

            if (refundRequestDto.getReceipt() != null) {
                jsonObject.put("receipt", refundRequestDto.getReceipt());
            }

            if (refundRequestDto.getRefundSpeed() != null) {
                jsonObject.put("speed", refundRequestDto.getRefundSpeed().name());
            }
        }
        return refundService.updateRefund(refundId, jsonObject);
    }
}
