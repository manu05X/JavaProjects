package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.CancelOrderRequestDto;
import org.example.evaluations.evaluation.exceptions.OrderNotFoundException;
import org.example.evaluations.evaluation.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @DeleteMapping
    public Boolean cancelOrder(@RequestBody CancelOrderRequestDto cancelOrderRequestDto) throws  OrderNotFoundException {
        try {
            return orderService.cancelOrder(cancelOrderRequestDto.getOrderId());
        }catch (OrderNotFoundException orderNotFoundException) {
            throw orderNotFoundException;
        }
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> throwExceptions(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
